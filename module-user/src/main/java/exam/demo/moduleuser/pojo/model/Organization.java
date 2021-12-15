package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 组织机构表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("organization")
public class Organization implements Serializable {

    /**
     * 组织机构ID
     */
    @ApiModelProperty(value = "组织机构ID")
    private Integer id;
    /**
     * 机构名
     */
    @ApiModelProperty(value = "机构名")
    private String name;
    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码")
    private String code;
    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String master;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String tel;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
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
    private Integer judgeId;

    @TableField(exist = false)
    private Long oldVersion;
}
