package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -7953153706743802777L;
    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 公司ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long companyId;

    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgId;

    /**
     * 角色名
     */
    @NotNull(message = "角色名不能为空")
    private String name;

    /**
     * 角色代号
     */
    @NotNull(message = "角色代号不能为空")
    private String code;

    /**
     * 角色备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 资源节点ID
     */
    private List<Long> resourceId;

    /**
     * 用户ID
     */
    private List<Long> userId;
}
