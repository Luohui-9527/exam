package exam.demo.moduleexam.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.RPCUtils;
import exam.demo.moduleexam.manage.PaperApi;
import exam.demo.moduleexam.pojo.DTO.grade.*;
import exam.demo.moduleexam.pojo.VO.grade.ExamGradeRecordQueryFormVO;
import exam.demo.moduleexam.pojo.VO.grade.ExamGradeRecordTableDataVO;
import exam.demo.moduleexam.pojo.VO.grade.MarkingPaperVO;
import exam.demo.moduleexam.pojo.VO.grade.MyAnswerVO;
import exam.demo.moduleexam.service.GradeService;
import exam.demo.moduleexam.utils.DateFormatUtil;
import exam.demo.moduleexam.utils.DateToString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("grade")
public class GradeController {

    private final static Integer PAGE_SIZE = 8;

    @Autowired
    PaperApi paperApi;

    @Autowired
    GradeService gradeService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public CommonResponse<PageVo<ExamGradeRecordTableDataVO>> query(@RequestBody CommonRequest<ExamGradeRecordQueryFormVO> commonRequest) {
        ExamGradeRecordQueryFormVO queryFormVO = commonRequest.getData();
        ExamGradeRecordQueryFormDTO queryFormDTO = CommonUtils.copyProperties(commonRequest.getData(), ExamGradeRecordQueryFormDTO.class);
        if (queryFormVO.getEndTimeRange() != null && queryFormVO.getEndTimeRange().size() != 0) {
            List<String> endTimeRange = DateToString.convert(queryFormVO.getEndTimeRange());
            queryFormDTO.setEndTimeRange(endTimeRange);
        }
        Page<ExamGradeRecordQueryFormVO> page = new Page<>(queryFormVO.getCurrentPage(), PAGE_SIZE);
        List<ExamGradeRecordTableDataDTO> tableDataDTOS = gradeService.query(queryFormDTO);
        List<ExamGradeRecordTableDataVO> tableDataVOS = new ArrayList<>();
        for (ExamGradeRecordTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamGradeRecordTableDataVO tableDataVO = CommonUtils.copyProperties(tableDataDTO, ExamGradeRecordTableDataVO.class);
            tableDataVO.setPaperName(getPaperName(tableDataDTO.getPaperId(), commonRequest));
            tableDataVO.setCreatedTime(DateFormatUtil.format(tableDataDTO.getCreatedTime()));
            tableDataVO.setActualEndTime(DateFormatUtil.format(tableDataDTO.getActualEndTime()));
            tableDataVO.setMarkingStopTime(DateFormatUtil.format(tableDataDTO.getMarkingStopTime()));
            setVoStatus(tableDataVO, tableDataDTO);
            tableDataVOS.add(tableDataVO);
        }
        PageVo<ExamGradeRecordTableDataVO> pageVo = PageMapUtil.getPageMap(tableDataVOS, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);

    }


    @MethodEnhancer
    @RequestMapping(value = "markingpaper", method = RequestMethod.POST)
    public CommonResponse<Boolean> markingPaper(@RequestBody CommonRequest<MarkingPaperVO> commonRequest) {
        MarkingPaperVO markingPaperVO = commonRequest.getData();
        MarkingPaperDTO markingPaperDTO = new MarkingPaperDTO();
        BeanUtils.copyProperties(markingPaperVO, markingPaperDTO);
        List<MarkingAnswerDTO> markingAnswerDTOS = CommonUtils.convertList(markingPaperVO.getMarkingAnswerVOList(), MarkingAnswerDTO.class);
        markingPaperDTO.setMarkingAnswerDTOList(markingAnswerDTOS);
        if (gradeService.markingPaper(markingPaperDTO)) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, false);
    }

    /**
     * 获取答题情况
     *
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "getPaperAnswer")
    public CommonResponse<List> getPaperAnswer(@RequestBody CommonRequest<Integer> commonRequest) {
        Integer examRecordId = commonRequest.getData();
        List<MyAnswerDTO> myAnswerDTOS = gradeService.getMyAnswer(examRecordId);
        List<MyAnswerVO> myAnswerVOS = CommonUtils.convertList(myAnswerDTOS, MyAnswerVO.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, myAnswerVOS);
    }

    private void setVoStatus(ExamGradeRecordTableDataVO tableDataVO, ExamGradeRecordTableDataDTO tableDataDTO) {
        if (tableDataDTO.getStatus() != null) {
            if (tableDataDTO.getStatus() == 1) {
                tableDataVO.setStatus("已批阅");
            } else if (tableDataDTO.getStatus() == 0) {
                tableDataVO.setStatus("未批阅");
            } else {
                tableDataVO.setStatus("阅卷过期");
            }
        }
    }

    private String getPaperName(Integer id, CommonRequest commonRequest) {
        if (id != null) {
            CommonRequest<Integer> request = new CommonRequest<>();
            request.setToken(commonRequest.getToken());
            request.setData(id);
            request.setVersion(commonRequest.getVersion());
            return RPCUtils.parseResponse(paperApi.queryPaperNameByPaperId(request), String.class, RPCUtils.PAPER);
        }
        return null;
    }

}
