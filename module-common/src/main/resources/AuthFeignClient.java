package exam.demo.modulecommon.feign;

import exam.demo.modulecommon.common.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 认证服务Feign客户端
 */
@FeignClient(name = "module-auth", contextId = "authFeignClient")
public interface AuthFeignClient {
    /**
     * 示例方法：验证用户权限
     */
    @PostMapping("/auth/validate")
    CommonResponse validatePermission(@RequestParam("userId") Long userId, @RequestParam("permission") String permission);

    /**
     * 示例方法：获取用户令牌
     */
    @PostMapping("/auth/token/get")
    CommonResponse getToken(@RequestBody Object request);
}
