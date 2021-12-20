package exam.demo.moduleexam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleexam.pojo.DTO.report.ExamDetailQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.VO.report.ExamDetailQueryFormVO;
import exam.demo.moduleexam.pojo.VO.report.ExamReportRecordDetailTableDataVO;
import exam.demo.moduleexam.pojo.VO.report.ExamReportRecordExamTableDataVO;
import exam.demo.moduleexam.pojo.VO.report.ExamReportRecordQueryFormVO;
import exam.demo.moduleexam.service.ReportService;
import exam.demo.moduleexam.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("report")
public class ReportController {
    private final static Integer PAGE_SIZE = 8;
    private final static double RANK_A = 0.9;
    private final static double RANK_B = 0.8;
    private final static double RANK_C = 0.6;

    @Autowired
    CommonState state;

    @Autowired
    ReportService reportService;


    @MethodEnhancer
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public CommonResponse<PageVo<ExamReportRecordExamTableDataVO>> query(@RequestBody ExamReportRecordQueryFormVO queryFormVO) {
        ExamReportRecordQueryFormDTO queryFormDTO = CommonUtils.copyProperties(queryFormVO, ExamReportRecordQueryFormDTO.class);

        if (queryFormVO.getExamTimeRange() != null && queryFormVO.getExamTimeRange().size() != 0) {
            queryFormDTO.setStartTime(queryFormVO.getExamTimeRange().get(0));
            queryFormDTO.setEndTime(queryFormVO.getExamTimeRange().get(1));
        }
        Page<ExamReportRecordQueryFormVO> page = new Page<>(queryFormVO.getCurrentPage(), PAGE_SIZE);
        List<ExamReportRecordExamTableDataDTO> tableDataDTOS = reportService.query(queryFormDTO);
        List<ExamReportRecordExamTableDataVO> tableDataVOS = new ArrayList<>();
        for (ExamReportRecordExamTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamReportRecordExamTableDataVO vo = CommonUtils.copyProperties(tableDataDTO, ExamReportRecordExamTableDataVO.class);
            vo.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
            tableDataVOS.add(vo);
        }
        PageVo<ExamReportRecordExamTableDataVO> pageVo = PageMapUtil.getPageMap(tableDataVOS, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @RequestMapping(value = "querydetail", method = RequestMethod.POST)
    public CommonResponse<PageVo<ExamReportRecordDetailTableDataVO>> queryDetail(@RequestBody ExamDetailQueryFormVO queryFormVO) {
        int rank = 0;
        ExamDetailQueryFormDTO queryFormDTO = CommonUtils.copyProperties(queryFormVO, ExamDetailQueryFormDTO.class);
        Page<ExamDetailQueryFormVO> page = new Page<>(queryFormVO.getCurrentPage(), 10);
        List<ExamReportRecordDetailTableDataDTO> tableDataDTOS = reportService.queryDetail(queryFormDTO);
        // 查询该考试的试卷满分是多少
        Double score = reportService.findMaxScore(queryFormVO.getId());
        List<ExamReportRecordDetailTableDataVO> tableDataVOS = new ArrayList<>();
        if (tableDataDTOS != null && tableDataDTOS.size() != 0) {
            for (ExamReportRecordDetailTableDataDTO tableDataDTO : tableDataDTOS) {
                ExamReportRecordDetailTableDataVO vo = CommonUtils.copyProperties(tableDataDTO, ExamReportRecordDetailTableDataVO.class);
                vo.setAbilityLabel(getLabel(tableDataDTO, score));
                vo.setExamCostTime(getCostTime(tableDataDTO));
                vo.setRanking(++rank + (queryFormVO.getCurrentPage() - 1) * PAGE_SIZE);
                tableDataVOS.add(vo);
            }
        }
        PageVo<ExamReportRecordDetailTableDataVO> pageVo = PageMapUtil.getPageMap(tableDataVOS, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    /**
     * 根据试卷总分的百分比计算等级
     *
     * @param dto
     * @return
     */
    private String getLabel(ExamReportRecordDetailTableDataDTO dto, Double score) {
        if (dto.getScore() != null) {
            if (dto.getScore() >= (RANK_A * score)) {
                return "A";
            } else if (dto.getScore() >= (RANK_B * score)) {
                return "B";
            } else if (dto.getScore() >= (RANK_C * score)) {
                return "C";
            } else {
                return "D";
            }
        }
        return null;
    }

    private String getCostTime(ExamReportRecordDetailTableDataDTO dto) {
        if (dto.getActualStartTime() != null && dto.getActualEndTime() != null) {
            Long costTime = (dto.getActualEndTime().getTime() - dto.getActualStartTime().getTime()) / (1000 * 60);
            return costTime.toString() + "分钟";
        }
        return null;
    }
}
