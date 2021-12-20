package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class SystemParamQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -8636652089292804036L;
    /**
     * 参数类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paramType;
    /**
     * 输入参数项
     */
    private String param;

    public SystemParamQueryVo() {
    }

    public Long getParamType() {
        return paramType;
    }

    public void setParamType(Long paramType) {
        this.paramType = paramType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "SystemParamQueryVo{" +
                "paramType='" + paramType + '\'' +
                ", param='" + param + '\'' +
                '}';
    }
}
