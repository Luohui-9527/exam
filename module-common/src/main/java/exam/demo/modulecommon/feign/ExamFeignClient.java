package exam.demo.modulecommon.feign;

import exam.demo.modulecommon.common.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 考试服务Feign客户端
 */
@FeignClient(name = "module-exam", contextId = "examFeignClient")
public interface ExamFeignClient {
    
    /**
     * 示例方法：获取考试信息
     */
    @PostMapping("/exam/get")
    CommonResponse getExamInfo(@RequestParam("examId") Long examId);
    
    /**
     * 示例方法：保存考试信息
     */
    @PostMapping("/exam/save")
    CommonResponse saveExamInfo(@RequestBody Object request);
}
