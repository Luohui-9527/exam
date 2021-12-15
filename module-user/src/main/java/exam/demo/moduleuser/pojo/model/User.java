package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 用户表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("user")
public class User implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Integer id;
    /**
     * 职位ID
     */
    @ApiModelProperty(value = "职位ID")
    private Integer positionId;
    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Integer departmentId;
    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Integer companyId;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String code;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String profilePicture;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer sex;
    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生日")
    private java.util.Date birthday;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String tel;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 其他联系
     */
    @ApiModelProperty(value = "其他联系")
    private String other;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 状态位
     */
    @ApiModelProperty(value = "状态位")
    private Integer status;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createdTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Integer updatedBy;
    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updatedTime;
    /**
     * 版本
     */
    @ApiModelProperty(value = "版本")
    private Long version;

    /**
     * 公司
     */
    @TableField(exist = false)
    private String companyName;

    /**
     * 部门
     */
    @TableField(exist = false)
    private String departmentName;

    /**
     * 职位
     */
    @TableField(exist = false)
    private String positionName;

    /**
     * 角色
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 角色ID
     */
    @TableField(exist = false)
    private Integer roleId;

    @TableField(exist = false)
    private Integer judgeId;

    @TableField(exist = false)
    private Long oldVersion;
}
