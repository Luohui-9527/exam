package exam.demo.moduleuser.manage;


import exam.demo.modulecommon.common.*;
import exam.demo.moduleuser.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-14
 */
@FeignClient(ApiConstant.SERVICE_NAME)
public interface BaseInfoApi {
    @PostMapping({"/baseinfo/list/category"})
    CommonResponse<BaseDataDto> listCategory(CommonRequest<BaseDataDto> request);

    @PostMapping({"/baseinfo/get/base/datas"})
    CommonResponse<BaseDataDto> getBaseDataS(CommonRequest<BaseDataDto> request);

    @PostMapping({"/baseinfo/get/base/data"})
    CommonResponse<String> getBaseData(CommonRequest<Integer> request);

    @PostMapping({"/baseinfo/get/subject/and/answer"})
    CommonResponse<SubjectPackage> getSubjectAndAnswer(CommonRequest<Integer> request);

    @PostMapping({"/baseinfo/get/subject/customized"})
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(CommonRequest<List<CombExamConfigItemDto>> request);

    @PostMapping({"/baseinfo/get/subject/by/id"})
    CommonResponse<SubjectPackage> getSubjectById(CommonRequest<List<Integer>> request);

    @PostMapping({"/baseinfo/list/subject/type"})
    CommonResponse<BaseDataDto> getSubjectType(CommonRequest<BaseDataDto> request);
}
