package exam.demo.modulebaseinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "exam.demo.modulecommon.feign")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan(basePackages = {"exam.demo.modulebaseinfo", "exam.demo.modulecommon"})
@MapperScan(basePackages = "exam.demo.modulebaseinfo.dao")
public class ModuleBaseinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleBaseinfoApplication.class, args);
    }

}
