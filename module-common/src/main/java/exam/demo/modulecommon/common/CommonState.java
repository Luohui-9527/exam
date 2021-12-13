package exam.demo.modulecommon.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
@Component
public class CommonState {
    public final String SUCCESS = "200";
    public final String FAIL = "100";
    public final String SUCCESS_MSG = "请求成功";
    public final String FAIL_MSG = "请求失败";

    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
