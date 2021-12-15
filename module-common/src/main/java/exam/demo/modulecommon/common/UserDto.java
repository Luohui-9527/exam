package exam.demo.modulecommon.common;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 *
 * @author
 */
@Data
public class UserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 3151530781112654208L;

    /**
     * 职位ID
     */
    private Integer positionId;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 公司Id
     */
    private Integer companyId;

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
     * 性别
     */
    private Byte sex;

    /**
     * 生日
     */
    private Date birthday;

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

    /**
     * 部门
     */
    private String departmentName;

    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 职位
     */
    private String positionName;

    /**
     * 角色
     */
    private String roleName;

    /**
     * 公司
     */
    private String companyName;

    private String ip;

    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private Integer judgeId;
}
