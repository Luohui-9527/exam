package exam.demo.moduleauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author luohui
 */
@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@MapperScan(basePackages = "exam.demo.moduleauth.dao")
public class ModuleAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleAuthApplication.class, args);
    }

}
