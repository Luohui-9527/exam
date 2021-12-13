package exam.demo.modulecommon.utils;

import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import exam.demo.modulecommon.utils.jwt.JwtUtil;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public class TokenUtils {
    public static String getToken(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            return request.getHeader("X-Token");
        }
        throw new StarterException(StarterError.SYSTEM_TOKEN_IS_NULL);
    }

    public static UserPermission getUser(){
        String token = getToken();
        try {
            return JwtUtil.parseJwt(token);
        } catch (Exception e) {
            throw new StarterException(StarterError.SYSTEM_TOKEN_PARSE_ERROR);
        }
    }
}
