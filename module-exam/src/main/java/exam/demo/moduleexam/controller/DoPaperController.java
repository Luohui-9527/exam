package exam.demo.moduleexam.controller;


import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PaperDetail;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.RPCUtils;
import exam.demo.moduleexam.manage.PaperApi;
import exam.demo.moduleexam.pojo.DTO.dopaper.DoPaperFormDTO;
import exam.demo.moduleexam.pojo.DTO.dopaper.UserInfoFormDTO;
import exam.demo.moduleexam.pojo.VO.dopaper.DoPaperFormVO;
import exam.demo.moduleexam.pojo.VO.dopaper.TimeWrapper;
import exam.demo.moduleexam.pojo.VO.dopaper.UserInfoFormVO;
import exam.demo.moduleexam.service.DoPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("dopaper")
public class DoPaperController {
    @Autowired
    CommonState state;

    @Autowired
    DoPaperService doPaperService;

    @Autowired
    PaperApi paperApi;

    /**
     * 保存考生信息
     *
     * @param userInfoFormVO
     * @return commonResponse
     */
    @MethodEnhancer
    @RequestMapping(value = "saveexaminer")
    public CommonResponse<String> saveExaminer(@RequestBody @Valid UserInfoFormVO userInfoFormVO) {
        UserInfoFormDTO userInfoFormDTO = CommonUtils.copyProperties(userInfoFormVO, UserInfoFormDTO.class);
        Long examId = doPaperService.saveExaminee(userInfoFormDTO);
        if (null != examId) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, examId.toString());
        }
        return new CommonResponse<>(state.SUCCESS, state.FAIL_MSG, null);
    }

    /**
     * 添加答案
     *
     * @param doPaperFormVOS
     * @return commonResponse
     */
    @MethodEnhancer
    @RequestMapping(value = "addanswer")
    public CommonResponse addAnswer(@RequestBody List<DoPaperFormVO> doPaperFormVOS) {
        List<DoPaperFormDTO> doPaperFormDTOS = CommonUtils.convertList(doPaperFormVOS, DoPaperFormDTO.class);
        doPaperService.addMyAnswer(doPaperFormDTOS);
        return new CommonResponse(state.SUCCESS, state.SUCCESS_MSG, "success");
    }

    /**
     * 获取试卷信息
     *
     * @param commonRequest paperId
     * @return commonResponse
     */
    @MethodEnhancer
    @RequestMapping(value = "getPaper")
    public CommonResponse<PaperDetail> getPaper(@RequestBody CommonRequest<Long> commonRequest) {
        PaperDetail detail = RPCUtils.parseResponse(paperApi.queryDetailByPaperId(commonRequest), PaperDetail.class, RPCUtils.PAPER);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, detail);
    }

    @MethodEnhancer
    @RequestMapping(value = "getExamTime")
    public CommonResponse<TimeWrapper> getExamTime(@RequestBody CommonRequest<Long> request) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, doPaperService.getExamTime(request.getData()));
    }
}
