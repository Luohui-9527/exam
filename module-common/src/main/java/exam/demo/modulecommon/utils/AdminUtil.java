package exam.demo.modulecommon.utils;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-19
 */
public class AdminUtil {
    public static boolean isSuperAdmin(){
        return TokenUtils.getUser().getId() == 1;
    }
}
