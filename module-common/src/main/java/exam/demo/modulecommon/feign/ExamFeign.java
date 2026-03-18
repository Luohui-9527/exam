package exam.demo.modulecommon.feign;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 考试服务 Feign 客户端
 * 用于内部调用 exam 服务
 *
 * @author luohui
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_EXAM)
public interface ExamFeign {
    @PostMapping({"/api/checkEditable"})
    CommonResponse<Boolean> checkEditable(@RequestBody Long paperId);
}
