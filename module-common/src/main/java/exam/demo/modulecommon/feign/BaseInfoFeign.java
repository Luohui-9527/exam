package exam.demo.modulecommon.feign;


import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.SubjectPackage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 基础信息服务 Feign 客户端
 * 用于内部调用 baseinfo 服务
 *
 * @author luohui
 */
@FeignClient(name = "baseinfo", path = "/baseinfo")
public interface BaseInfoFeign {
    @PostMapping("/category/list")
    CommonResponse<BaseDataDto> listCategory(@RequestBody BaseDataDto request);

    @PostMapping("/dictionary/list")
    CommonResponse<BaseDataDto> getBaseDataS(@RequestBody BaseDataDto request);

    @PostMapping("/dictionary/val")
    CommonResponse<String> getBaseData(@RequestBody String request);

    @PostMapping("/subject/package")
    CommonResponse<SubjectPackage> getSubjectAndAnswer(@RequestBody String request);

    @PostMapping("/subject/customized")
    CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(@RequestBody List<CombExamConfigItemDto> request);

    @PostMapping("/subject/byId")
    CommonResponse<SubjectPackage> getSubjectById(@RequestBody List<String> request);

    @PostMapping("/subjectType/list")
    CommonResponse<BaseDataDto> getSubjectTypeList(@RequestBody BaseDataDto request);

    @PostMapping("/category/value")
    CommonResponse<String> getCategory(@RequestBody String request);

    @PostMapping("/subjectType/value")
    CommonResponse<String> getSubjectTypeValue(@RequestBody String request);
}
