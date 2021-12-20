package exam.demo.moduleauth.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 *
 * @author
 */
@Data
public class UserDto implements Serializable {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 部门ID
     */
    private Long departmentId;

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
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private Long updatedBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 版本
     */
    private Long version;

    private String companyName;
    private String ip;
    private static final long serialVersionUID = 1L;

}
