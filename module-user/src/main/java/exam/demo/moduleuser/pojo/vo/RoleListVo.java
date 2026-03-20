package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class RoleListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -345011928472638396L;
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
     * 角色代号
     */
    private String code;

    /**
     * 角色备注
     */
    private String remark;

    /**
     * 所属机构
     */
    private String orgName;

    /**
     * 所属公司
     */
    private String companyName;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 资源节点ID
     */
    private String resourceId;

}
