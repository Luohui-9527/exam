package exam.demo.modulecommon.feign;


import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.SubjectPackage;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 基础信息服务 Feign 客户端
 * 用于内部调用 baseinfo 服务
 * @author luohui
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_BASE_INFO, path = ApiConstant.SERVICE_VALUE_BASE_INFO)
public interface BaseInfoFeign {
    @PostMapping({"/list/category"})
    CommonResponse<BaseDataDto> listCategory(@RequestBody BaseDataDto request);

    @PostMapping({"/get/base/datas"})
    CommonResponse<BaseDataDto> getBaseDataS(@RequestBody BaseDataDto request);

    @PostMapping({"/get/base/data"})
    CommonResponse<String> getBaseData(@RequestBody Long request);

    @PostMapping({"/get/subject/and/answer"})
    CommonResponse<SubjectPackage> getSubjectAndAnswer(@RequestBody Long request);

    @PostMapping({"/get/subject/customized"})
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(@RequestBody List<CombExamConfigItemDto> request);

    @PostMapping({"/get/subject/by/id"})
    CommonResponse<SubjectPackage> getSubjectById(@RequestBody List<Long> request);

    @PostMapping({"/list/subject/type"})
    CommonResponse<BaseDataDto> getSubjectType(@RequestBody BaseDataDto request);

    @PostMapping({"/get/category/val"})
    CommonResponse<String> getCategory(@RequestBody Long request);

    @PostMapping({"/get/subject/type"})
    CommonResponse<String> getSubjectType(@RequestBody Long request);
}
