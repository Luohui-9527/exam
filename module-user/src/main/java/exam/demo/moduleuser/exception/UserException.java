package exam.demo.moduleuser.exception;


import exam.demo.modulecommon.exception.NestedExamException;
import exam.demo.modulecommon.exception.StarterError;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-07
 */
public class UserException extends NestedExamException {
    public UserException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }

    public UserException(StarterError error) {
        super(error.getMsg(), error.getCode());
    }

    public UserException(UserError error) {
        super(error.getMsg(), error.getCode());
    }

    public UserException(UserError error, Object... args) {
        super(String.format(error.getMsg(), args), error.getCode());
    }
}
