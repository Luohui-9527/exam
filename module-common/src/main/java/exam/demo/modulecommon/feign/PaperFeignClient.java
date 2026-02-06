package exam.demo.modulecommon.feign;

import exam.demo.modulecommon.common.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 试卷服务Feign客户端
 */
@FeignClient(name = "module-paper", contextId = "paperFeignClient")
public interface PaperFeignClient {
    
    /**
     * 示例方法：获取试卷信息
     */
    @PostMapping("/paper/get")
    CommonResponse getPaperInfo(@RequestParam("paperId") Long paperId);
    
    /**
     * 示例方法：保存试卷信息
     */
    @PostMapping("/paper/save")
    CommonResponse savePaperInfo(@RequestBody Object request);
}
