package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {
    private String version;
    private String code;
    private String msg;
    private T data;

    public CommonResponse(String code, String msg, T data){
        this.code =code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "version='" + version + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
