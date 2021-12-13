package exam.demo.modulecommon.exception;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
public class NestedExamException extends RuntimeException{
    private String errorCode;

    public NestedExamException(String errorMessage, String errorCode){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
