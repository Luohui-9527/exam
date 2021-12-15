package exam.demo.moduleauth.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 资源表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("resource")
public class Resource implements Serializable {

    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源ID")
    private Integer id;
    /**
     * 节点名称
     */
    @ApiModelProperty(value = "节点名称")
    private String name;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;
    /**
     * 顺序号
     */
    @ApiModelProperty(value = "顺序号")
    private Integer orderIndex;
    /**
     * 父亲节点
     */
    @ApiModelProperty(value = "父亲节点")
    private Integer parentId;
    /**
     * URL
     */
    @ApiModelProperty(value = "URL")
    private String url;
    /**
     * 打开图标
     */
    @ApiModelProperty(value = "打开图标")
    private String openImg;
    /**
     * 关闭图标
     */
    @ApiModelProperty(value = "关闭图标")
    private String closeImg;
    /**
     * 资源类型
     */
    @ApiModelProperty(value = "资源类型")
    private Integer resourceType;
    /**
     * 叶子节点
     */
    @ApiModelProperty(value = "叶子节点")
    private Integer leaf;
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
}
