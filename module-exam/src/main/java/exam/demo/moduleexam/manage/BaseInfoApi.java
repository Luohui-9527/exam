package exam.demo.moduleexam.manage;


import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.SubjectPackage;
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
    CommonResponse<BaseDataDto> listCategory(BaseDataDto baseDataDto);

    @PostMapping({"/get/base/datas"})
    CommonResponse<BaseDataDto> getBaseDataS(BaseDataDto baseDataDto);

    @PostMapping({"/get/base/data"})
    CommonResponse<String> getBaseData(Long id);

    @PostMapping({"/get/subject/and/answer"})
    CommonResponse<SubjectPackage> getSubjectAndAnswer(Long id);

    @PostMapping({"/get/subject/customized"})
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(List<CombExamConfigItemDto> combExamConfigItemDtoList);

    @PostMapping({"/get/subject/by/id"})
    CommonResponse<SubjectPackage> getSubjectById(List<Long> subjectIdList);

    @PostMapping({"/list/subject/type"})
    CommonResponse<BaseDataDto> getSubjectType(BaseDataDto baseDataDto);

    @PostMapping({"/get/category/val"})
    CommonResponse<String> getCategory(Long id);

    @PostMapping({"/get/subject/type"})
    CommonResponse<String> getSubjectType(Long id);
}
