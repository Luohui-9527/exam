package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 系统配置表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("system_param")
public class SystemParam implements Serializable {

    /**
     * 系统参数ID
     */
    @ApiModelProperty(value = "系统参数ID")
    private Integer id;
    /**
     * 组织机构ID
     */
    @ApiModelProperty(value = "组织机构ID")
    private Integer orgId;
    /**
     * 参数类型
     */
    @ApiModelProperty(value = "参数类型")
    private Integer paramType;
    /**
     * 参数项
     */
    @ApiModelProperty(value = "参数项")
    private String param;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String value;
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

    @TableField(exist = false)
    private Long oldVersion;
}
