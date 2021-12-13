package exam.demo.modulepaper.exception;


import exam.demo.modulecommon.exception.NestedExamException;
import exam.demo.modulecommon.exception.StarterError;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public class PaperException extends NestedExamException {

    public PaperException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }

    public PaperException(StarterError error) {
        super(error.getMsg(), error.getCode());
    }

    public PaperException(PaperError error) {
        super(error.msg, error.code);
    }

    public PaperException(PaperError error, Object... o) {
        super(String.format(error.msg, o), error.code);
    }

    public PaperException(StarterError error, Object... o) {
        super(String.format(error.getMsg(), o), error.getCode());
    }
}
