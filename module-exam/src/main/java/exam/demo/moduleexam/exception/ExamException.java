package exam.demo.moduleexam.exception;


import exam.demo.modulecommon.exception.NestedExamException;
import exam.demo.modulecommon.exception.StarterError;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
public class ExamException extends NestedExamException {
    public ExamException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }

    public ExamException(ExamError error) {
        super(error.msg, error.code);
    }

    public ExamException(StarterError error) {
        super(error.getMsg(), error.getCode());
    }

    public ExamException(StarterError error, Object... o) {
        super(String.format(error.getMsg(), o), error.getCode());
    }

    public ExamException(ExamError error, Object... o) {
        super(String.format(error.msg, o), error.code);
    }
}
