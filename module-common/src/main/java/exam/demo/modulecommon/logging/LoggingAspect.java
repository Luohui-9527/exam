package exam.demo.modulecommon.logging;

import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
@Slf4j
@Aspect
@Configuration
public class LoggingAspect {
    public String GET = "GET";

    private String version;
    public void setVersion(String version) {
        this.version = version;
    }

    @Pointcut("@annotation(exam.demo.modulecommon.logging.annotation.MethodEnhancer)")
    public void joinPoint(){}

    @Before(value = "exam.demo.modulecommon.logging.LoggingAspect.joinPoint()")
    public void logRequest(JoinPoint joinPoint){
        Object[] arg = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("\n\n\n请求的方法为:{}\n",joinPoint.getSignature().getName());
        if (request != null && GET.equals(request.getMethod())){
            return;
        }
        if (arg.length != 0){
            for (Object o : arg) {
                log.info("\n请求参数为: {}\n\n",o.toString());
                if (o instanceof CommonRequest){
                    // 版本不匹配
                    if (version != null && !version.equals(((CommonRequest) o).getVersion())){
                        throw new StarterException(StarterError.SYSTEM_VERSION_NOT_MATCH);
                    }
                }
            }
        }else {
            throw new StarterException(StarterError.SYSTEM_PARAMETER_IS_NULL);
        }
    }

    @AfterReturning(value = "exam.demo.modulecommon.logging.LoggingAspect.joinPoint()",returning = "o")
    public void logResponse(JoinPoint joinPoint, Object o){
        log.info("\n\n{}方法响应为: {}\n\n",joinPoint.getSignature().getName(),o.toString());
        if (o instanceof CommonResponse){
            ((CommonResponse) o).setVersion(version);
        }
    }
}
