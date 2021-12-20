package exam.demo.modulepaper.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 试卷表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("paper")
public class Paper implements Serializable {

    /**
     * 试卷id
     */
    @ApiModelProperty(value = "试卷id")
    private Long id;
    /**
     * 试卷名
     */
    @ApiModelProperty(value = "试卷名")
    private String name;
    /**
     * 试卷类型
     */
    @ApiModelProperty(value = "试卷类型")
    private Long paperType;
    /**
     * 难度
     */
    @ApiModelProperty(value = "难度")
    private Long difficulty;
    /**
     * 组卷日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "组卷日期")
    private Date combExamTime;
    /**
     * 组卷人
     */
    @ApiModelProperty(value = "组卷人")
    private String paperCreator;
    /**
     * 组卷人
     */
    @ApiModelProperty(value = "组卷人")
    private String combExamer;
    /**
     * 卷面分数
     */
    @ApiModelProperty(value = "卷面分数")
    private Double score;
    /**
     * 卷子描述
     */
    @ApiModelProperty(value = "卷子描述")
    private String description;
    /**
     * 是否为模板
     */
    @ApiModelProperty(value = "是否为模板")
    private Integer template;
    /**
     * 下载次数
     */
    @ApiModelProperty(value = "下载次数")
    private Integer downloadTimes;
    /**
     * 发布次数
     */
    @ApiModelProperty(value = "发布次数")
    private Integer publishTimes;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 机构id
     */
    @ApiModelProperty(value = "机构id")
    private Long orgId;
    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    private Long companyId;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private Long createdBy;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private Long updatedBy;
    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    /**
     * 版本
     */
    @ApiModelProperty(value = "版本")
    private Long version;
}
