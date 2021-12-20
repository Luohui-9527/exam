package exam.demo.modulepaper.manager.baseinfo;


import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_BASE_INFO, value = ApiConstant.SERVICE_NAME_BASE_INFO)
public interface BaseInfoApi {
    @PostMapping({"/baseinfo/list/category"})
    CommonResponse listCategory(BaseDataDto baseDataDto);

    @PostMapping({"/baseinfo/get/base/datas"})
    CommonResponse getBaseDataS(BaseDataDto baseDataDto);

    @PostMapping({"/baseinfo/get/base/data"})
    CommonResponse getBaseData(Long dictionaryId);

    @PostMapping({"/baseinfo/get/subject/and/answer"})
    CommonResponse getSubjectAndAnswer(Long combExamConfigId);

    @PostMapping({"/baseinfo/get/subject/customized"})
    CommonResponse getSubjectAndAnswerCustomized(List<CombExamConfigItemDto> combExamConfigItemDtoList);

    @PostMapping({"/baseinfo/get/subject/by/id"})
    CommonResponse getSubjectById(List<Long> subjectIdList);

    @PostMapping({"/baseinfo/list/subject/type"})
    CommonResponse getSubjectType(BaseDataDto baseDataDto);

    @PostMapping({"/baseinfo/get/category/val"})
    CommonResponse getCategory(Long id);

    @PostMapping({"/baseinfo/get/subject/type"})
    CommonResponse getSubjectType(Long id);
}
