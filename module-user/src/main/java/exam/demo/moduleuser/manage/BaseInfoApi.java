package exam.demo.moduleuser.manage;


import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.SubjectPackage;
import exam.demo.moduleuser.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-14
 */
@FeignClient(ApiConstant.SERVICE_NAME)
public interface BaseInfoApi {
    @PostMapping({"/baseinfo/list/category"})
    CommonResponse<BaseDataDto> listCategory(@RequestBody BaseDataDto request);

    @PostMapping({"/baseinfo/get/base/datas"})
    CommonResponse<BaseDataDto> getBaseDataS(@RequestBody BaseDataDto request);

    @PostMapping({"/baseinfo/get/base/data"})
    CommonResponse<String> getBaseData(@RequestBody Long request);

    @PostMapping({"/baseinfo/get/subject/and/answer"})
    CommonResponse<SubjectPackage> getSubjectAndAnswer(@RequestBody Long request);

    @PostMapping({"/baseinfo/get/subject/customized"})
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(@RequestBody List<CombExamConfigItemDto> request);

    @PostMapping({"/baseinfo/get/subject/by/id"})
    CommonResponse<SubjectPackage> getSubjectById(@RequestBody List<Long> request);

    @PostMapping({"/baseinfo/list/subject/type"})
    CommonResponse<BaseDataDto> getSubjectType(@RequestBody BaseDataDto request);
}
