package exam.demo.moduleuser.dto;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于获取符合条件的用户名单
 */
public class UserForRoleDto implements Serializable {
    private static final long serialVersionUID = -5516751903890460865L;
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户工号
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户角色
     */
    private String roleName;
    /**
     * 用户部门
     */
    private String departmentName;
    /**
     * 用户职位
     */
    private String positionName;
    /**
     * 标记位：是否已有此角色
     */
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserForRole{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", positionName='" + positionName + '\'' +
                ", flag=" + flag +
                '}';
    }
}
