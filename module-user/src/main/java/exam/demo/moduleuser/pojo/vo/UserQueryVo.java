package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class UserQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = 1710678390025284785L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 工号
     */
    private String code;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 角色代号
     */
    private String roleCode;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer roleId;

    public UserQueryVo() {
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return "UserQueryVo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", tel='" + tel + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
