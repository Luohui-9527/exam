package exam.demo.moduleuser.dto;

import java.io.Serializable;

/**
 * @author acer
 */
public class RoleResourceDto implements Serializable {
    private static final long serialVersionUID = -1857819275686602576L;
    private Integer id;
    private Integer roleId;
    private Integer resourceId;
    private Byte type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoleResourceVO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", type=" + type +
                '}';
    }
}
