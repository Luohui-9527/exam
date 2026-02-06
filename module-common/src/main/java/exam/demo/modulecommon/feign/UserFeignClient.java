package exam.demo.modulecommon.feign;

import exam.demo.modulecommon.common.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务Feign客户端
 */
@FeignClient(name = "module-user", contextId = "userFeignClient")
public interface UserFeignClient {
    
    /**
     * 示例方法：获取用户信息
     */
    @PostMapping("/user/get")
    CommonResponse getUserInfo(@RequestParam("userId") Long userId);
    
    /**
     * 示例方法：保存用户信息
     */
    @PostMapping("/user/save")
    CommonResponse saveUserInfo(@RequestBody Object request);
}
