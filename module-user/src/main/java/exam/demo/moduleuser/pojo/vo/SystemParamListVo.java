package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class SystemParamListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -3209458874602625119L;
    /**
     * 系统参数ID
     */
    private String id;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 参数类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paramType;

    private String paramTypeName;
    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

    /**
     * 状态位
     */
    private Byte status;

}
