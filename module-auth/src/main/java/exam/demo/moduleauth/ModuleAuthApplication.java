package exam.demo.moduleauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luohui
 */
@Slf4j
@EnableScheduling
@EnableFeignClients
@EnableTransactionManagement
@SpringCloudApplication
public class ModuleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAuthApplication.class, args);
    }

}
