package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 */
@Data
public class BaseListVo implements Serializable {


    private static final long serialVersionUID = -8902900761862410246L;
    /**
     * id
     */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    protected Integer id;

    /**
     * 类名
     */
    protected String name;

    /**
     * 版本号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;

    /**
     * 状态位
     */
    @Max(2)
    protected Byte status;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    protected Date updatedTime;

    /**
     * 备注
     */
    protected String remark;
}
