package exam.demo.moduleuser.dto;

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
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 组织机构ID
     */
    private String orgId;

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
    private List<String> resourceId;

    /**
     * 用户ID
     */
    private List<String> userId;

    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private String judgeId;

    public RoleDto(String id) {
        this.id = id;
    }
}
