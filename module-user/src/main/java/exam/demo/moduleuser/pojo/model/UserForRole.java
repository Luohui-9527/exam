package exam.demo.moduleuser.pojo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserForRole implements Serializable {
    private static final long serialVersionUID = 7602346185553422472L;
    /**
     * 用户ID
     */
    private Long id;
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
