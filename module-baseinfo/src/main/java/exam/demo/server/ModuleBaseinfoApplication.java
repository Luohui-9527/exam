package exam.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ModuleBaseinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleBaseinfoApplication.class, args);
    }

}
