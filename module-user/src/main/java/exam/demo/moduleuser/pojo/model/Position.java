package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 职位表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("position")
public class Position implements Serializable {

    /**
     * 职位ID
     */
    @ApiModelProperty(value = "职位ID")
    private Integer id;
    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Integer companyId;
    /**
     * 职位名称
     */
    @ApiModelProperty(value = "职位名称")
    private String name;
    /**
     * 职位编号
     */
    @ApiModelProperty(value = "职位编号")
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
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updatedTime;
    /**
     * 版本
     */
    @ApiModelProperty(value = "版本")
    private Long version;

    /**
     * 所属公司
     */
    @TableField(exist = false)
    private String companyName;

    @TableField(exist = false)
    private Integer judgeId;

    @TableField(exist = false)
    private Long oldVersion;

}
