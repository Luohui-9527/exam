package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class UserItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 4116035229776194913L;
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
     * 公司ID
     */
    private String companyId;

    /**
     * 工号
     */
    @NotNull(message = "工号不能为空")
    private String code;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String name;

    /**
     * 头像
     */
    private String profilePicture;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    private Byte sex;

    /**
     * 生日
     */
    @NotNull(message = "生日不能为空")
    private Date birthday;

    /**
     * 电话
     */
    @NotNull(message = "电话不能为空")
    private String tel;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空")
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
     * 角色ID
     */
    private String roleId;

}
