package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 */
@Data
public class BaseItemVo implements Serializable {


    private static final long serialVersionUID = 8706224410388879778L;
    /**
     * id
     */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    protected Integer id;

    /**
     * 状态位
     */
    @Max(2)
    @NotNull(message = "状态位不能为空！")
    protected Byte status;

    /**
     * 版本号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;

    /**
     * 备注
     */
    protected String remark;
}
