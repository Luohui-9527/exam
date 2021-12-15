package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class SystemParamItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 7757535022165772960L;
    /**
     * 系统参数ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer orgId;

    /**
     * 参数类型
     */
    @NotNull(message = "参数类型不能为空")
    private Integer paramType;

    /**
     * 参数项
     */
    @NotNull(message = "参数项不能为空")
    private String param;

    /**
     * 参数值
     */
    @NotNull(message = "参数值不能为空")
    private String value;

    /**
     * 状态位
     */
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public SystemParamItemVo() {
    }

    @Override
    public String toString() {
        return "SystemParamItemVo{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", paramType='" + paramType + '\'' +
                ", param='" + param + '\'' +
                ", value='" + value + '\'' +
                ", status=" + status +
                '}';
    }
}
