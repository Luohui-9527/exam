package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = -6703991697359106264L;
    /**
     * 用户ID
     */
    private String id;

    /**
     * 职位ID
     */
    private String positionId;

    /**
     * 部门ID
     */
    private String departmentId;

    /**
     * 工号
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String profilePicture;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 职位名
     */
    private String positionName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 其他联系
     */
    private String other;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Byte status;

    private String ip;

}
