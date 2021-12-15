package exam.demo.moduleexam.manage;


import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
@FeignClient(ApiConstant.SERVICE_NAME_BASE_INFO)
public interface BaseInfoApi {
    @PostMapping({"/list/category"})
    CommonResponse<BaseDataDto> listCategory(CommonRequest<BaseDataDto> request);

    @PostMapping({"/get/base/datas"})
    CommonResponse<BaseDataDto> getBaseDataS(CommonRequest<BaseDataDto> request);

    @PostMapping({"/get/base/data"})
    CommonResponse<String> getBaseData(CommonRequest<Integer> request);

    @PostMapping({"/get/subject/and/answer"})
    CommonResponse<SubjectPackage> getSubjectAndAnswer(CommonRequest<Integer> request);

    @PostMapping({"/get/subject/customized"})
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(CommonRequest<List<CombExamConfigItemDto>> request);

    @PostMapping({"/get/subject/by/id"})
    CommonResponse<SubjectPackage> getSubjectById(CommonRequest<List<Integer>> request);

    @PostMapping({"/list/subject/type"})
    CommonResponse<BaseDataDto> getSubjectType(CommonRequest<BaseDataDto> request);

    @PostMapping({"/get/category/val"})
    CommonResponse<String> getCategory(Integer id);

    @PostMapping({"/get/subject/type"})
    CommonResponse<String> getSubjectType(Integer id);
}
