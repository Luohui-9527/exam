package exam.demo.modulecommon.feign;

import exam.demo.modulecommon.common.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 基础信息服务Feign客户端
 */
@FeignClient(name = "module-baseinfo", contextId = "baseInfoFeignClient")
public interface BaseInfoFeignClient {
    
    /**
     * 示例方法：获取基础配置信息
     */
    @PostMapping("/baseinfo/config/get")
    CommonResponse getConfig(@RequestParam("configKey") String configKey);
    
    /**
     * 示例方法：保存基础信息
     */
    @PostMapping("/baseinfo/save")
    CommonResponse saveBaseInfo(@RequestBody Object request);
}
