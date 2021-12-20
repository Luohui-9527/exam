package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 公司表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("company")
public class Company implements Serializable {

    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID")
    private Long id;
    /**
     * 组织机构ID
     */
    @ApiModelProperty(value = "组织机构ID")
    private Long orgId;
    /**
     * 公司名
     */
    @ApiModelProperty(value = "公司名")
    private String name;
    /**
     * 公司编号
     */
    @ApiModelProperty(value = "公司编号")
    private String code;
    /**
     * 助记码
     */
    @ApiModelProperty(value = "助记码")
    private String mnemonicCode;
    /**
     * 法人
     */
    @ApiModelProperty(value = "法人")
    private String master;
    /**
     * 税号
     */
    @ApiModelProperty(value = "税号")
    private String tax;
    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;
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
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 网址
     */
    @ApiModelProperty(value = "网址")
    private String website;
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

    @TableField(exist = false)
    private Long judgeId;

    @TableField(exist = false)
    private Long oldVersion;

    /**
     * 非表对应字段
     * 所属机构
     */
    @TableField(exist = false)
    private String orgName;
}
