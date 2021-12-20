package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 部门表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("department")
public class Department implements Serializable {

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long id;
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Long companyId;
    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;
    /**
     * 助记码
     */
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;
    /**
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    private String code;
    /**
     * 部门级别
     */
    @ApiModelProperty(value = "部门级别")
    private String level;
    /**
     * 上级部门
     */
    @ApiModelProperty(value = "上级部门")
    private Long parentId;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String master;
    /**
     * 部门描述
     */
    @ApiModelProperty(value = "部门描述")
    private String description;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
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

    @TableField(exist = false)
    private Long judgeId;

    @TableField(exist = false)
    private Long oldVersion;
}
