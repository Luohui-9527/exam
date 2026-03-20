package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class UserListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 4924379383104659009L;
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
     * 公司Id
     */
    private String companyId;

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
     * 公司名
     */
    private String companyName;

    /**
     * 部门
     */
    private String departmentName;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 生日
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
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

}
