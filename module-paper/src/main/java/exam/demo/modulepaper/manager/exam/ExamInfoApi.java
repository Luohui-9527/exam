package exam.demo.modulepaper.manager.exam;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_EXAM, path = ApiConstant.SERVICE_VALUE_EXAM)
public interface ExamInfoApi {
    @PostMapping({"/api/checkEditable"})
    CommonResponse<Boolean> checkEditable(@RequestBody Integer paperId);
}
