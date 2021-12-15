package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于角色页面分配用户
 */
public class RoleUserVo implements Serializable {
    private static final long serialVersionUID = -7978436944833582391L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer userId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer roleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "RoleUserVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", companyId=" + companyId +
                '}';
    }
}
