package exam.demo.moduleexam.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.RPCUtils;
import exam.demo.moduleexam.manage.UserApi;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import exam.demo.moduleexam.pojo.VO.answersheet.ExamAnswerSheetRecordQueryFormVO;
import exam.demo.moduleexam.pojo.VO.answersheet.ExamAnswerSheetRecordTableDataVO;
import exam.demo.moduleexam.service.AnswerSheetRecordService;
import exam.demo.moduleexam.utils.DateFormatUtil;
import exam.demo.moduleexam.utils.DateToString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/answersheet")
public class AnswerSheetRecordController {
    @Autowired
    CommonState state;

    @Autowired
    private AnswerSheetRecordService answerSheetRecordService;

    @Autowired
    private UserApi userFeignClient;

    private final static Integer PAGE_SIZE = 8;

    @MethodEnhancer
    @PostMapping(value = "/getanswersheet")
    public CommonResponse<PageVo<ExamAnswerSheetRecordTableDataVO>> queryAll(@RequestBody ExamAnswerSheetRecordQueryFormVO queryFormVO) {
        ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO = new ExamAnswerSheetRecordQueryFormDTO();
        BeanUtils.copyProperties(queryFormVO, examAnswerSheetRecordQueryFormDTO);
        if (queryFormVO.getPublisher() != null) {
            examAnswerSheetRecordQueryFormDTO.setPublisher(getPublisherId(queryFormVO.getPublisher()));
        }
        if (queryFormVO.getExamTimeRange() != null && queryFormVO.getExamTimeRange().size() != 0) {
            List<String> examTimeRange = DateToString.convert(queryFormVO.getExamTimeRange());
            examAnswerSheetRecordQueryFormDTO.setExamTimeRange(examTimeRange);
        }
        Page<ExamAnswerSheetRecordQueryFormVO> page = new Page<>(queryFormVO.getCurrentPage(), PAGE_SIZE);
        List<ExamAnswerSheetRecordTableDataDTO> tableDataDTOS = answerSheetRecordService.queryAnswerSheet(examAnswerSheetRecordQueryFormDTO);
        List<ExamAnswerSheetRecordTableDataVO> tableDataVOS = new ArrayList<ExamAnswerSheetRecordTableDataVO>();
        for (ExamAnswerSheetRecordTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamAnswerSheetRecordTableDataVO tableDataVO = new ExamAnswerSheetRecordTableDataVO();
            BeanUtils.copyProperties(tableDataDTO, tableDataVO);
            tableDataVO.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
            tableDataVO.setActualEndTime(DateFormatUtil.format(tableDataDTO.getActualEndTime()));
            tableDataVO.setActualStartTime(DateFormatUtil.format(tableDataDTO.getActualStartTime()));
            tableDataVO.setPublisher(getPublisherName(tableDataDTO.getPublisher()));
            tableDataVOS.add(tableDataVO);
        }
        PageVo<ExamAnswerSheetRecordTableDataVO> pageVo = PageMapUtil.getPageMap(tableDataVOS, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    /**
     * 通过id获取userName
     *
     * @param id
     * @return
     */
    private String getPublisherName(Long id) {
        if (id != null) {
            return RPCUtils.parseResponse(userFeignClient.getUserNameById(id), String.class, RPCUtils.USER);
        }
        return null;
    }

    /**
     * 通过名字获取人物id
     *
     * @param name
     * @return
     */
    private Long getPublisherId(String name) {
        if (name != null) {
            Long id = RPCUtils.parseResponse(userFeignClient.getUserIdByName(name), Long.class, RPCUtils.USER);
            if (id != null) {
                return id;
            } else {
                return 0L;
            }
        }
        return null;
    }
}
