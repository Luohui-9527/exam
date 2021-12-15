package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class RoleItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -7953153706743802777L;
    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 公司ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;

    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer orgId;

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
    private List<Integer> resourceId;

    /**
     * 用户ID
     */
    private List<Integer> userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public RoleItemVo() {
    }

    @Override
    public String toString() {
        return "RoleItemVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", orgId=" + orgId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", resourceId=" + resourceId +
                ", userId=" + userId +
                '}';
    }

    public List<Integer> getResourceId() {
        return resourceId;
    }

    public void setResourceId(List<Integer> resourceId) {
        this.resourceId = resourceId;
    }

    public List<Integer> getUserId() {
        return userId;
    }

    public void setUserId(List<Integer> userId) {
        this.userId = userId;
    }
}
