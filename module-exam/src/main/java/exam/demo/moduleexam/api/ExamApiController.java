package exam.demo.moduleexam.api;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.constant.ApiConstant;
import exam.demo.modulecommon.constant.ControllerConstant;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.moduleexam.mapper.ExamPublishRecordMapper;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
@RestController
@RequestMapping(ControllerConstant.API)
public class ExamApiController {
    @Resource
    ExamPublishRecordMapper examPublishRecordMapper;

    @Autowired
    CommonState state;

    /**
     * 试卷服务用来判断是否可以删除
     *
     * @param paperId
     * @return
     */
    @PostMapping(ApiConstant.CHECK_EDITABLE)
    public CommonResponse<Boolean> checkEditable(@RequestBody Long paperId) {
        List<ExamPublishRecord> record = examPublishRecordMapper.listByPaperId(paperId);
        if (CommonUtils.isEmpty(record)) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, false);
    }
}
