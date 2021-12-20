package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 权限表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("role")
public class Role implements Serializable {

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Long id;
    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    /**
     * 组织机构id
     */
    @ApiModelProperty(value = "组织机构id")
    private Long orgId;
    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String name;
    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码")
    private String code;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 状态位
     */
    @ApiModelProperty(value = "状态位")
    private Long status;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createdBy;
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
    private Long updatedBy;
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
     * 所属机构
     */
    @TableField(exist = false)
    private String orgName;

    /**
     * 所属公司
     */
    @TableField(exist = false)
    private String companyName;

    /**
     * 资源节点ID
     */
    @TableField(exist = false)
    private Long resourceId;

    /**
     * 用户ID
     */
    @TableField(exist = false)
    private Long userId;

    @TableField(exist = false)
    private Long judgeId;

    @TableField(exist = false)
    private Long oldVersion;
}
