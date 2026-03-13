package exam.demo.moduleuser.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 7246277876403033644L;
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
    private String name;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Long status;

    /**
     * 所属机构
     */
    private String orgName;

    /**
     * 所属公司
     */
    private String companyName;

    /**
     * 资源节点ID
     */
    private List<Long> resourceId;

    /**
     * 用户ID
     */
    private List<Long> userId;

    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private Long judgeId;

    public RoleDto(Long id) {
        this.id = id;
    }
}
