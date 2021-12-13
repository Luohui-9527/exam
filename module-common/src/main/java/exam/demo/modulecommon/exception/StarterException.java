package exam.demo.modulecommon.exception;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
public class StarterException extends NestedExamException {

    public StarterException(String errorMessage, String errorCode) {
        super(errorMessage, errorCode);
    }

    public StarterException(StarterError error){
        super(error.getMsg(),error.getCode());
    }

    public StarterException(StarterError error, Object ... o){
        super(String.format(error.getMsg(),o),error.getCode());
    }
}
