package exam.demo.moduleuser.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于获取符合条件的用户名单
 */
@Data
public class UserForRoleDto implements Serializable {
    private static final long serialVersionUID = -5516751903890460865L;
    /**
     * 用户ID
     */
    private String id;
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
    private Long flag;

}
