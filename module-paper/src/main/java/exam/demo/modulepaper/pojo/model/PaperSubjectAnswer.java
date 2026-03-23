package exam.demo.modulepaper.pojo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 试卷答案表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("paper_subject_answer")
public class PaperSubjectAnswer implements Serializable {

    /**
     * 答案id
     */
    @ApiModelProperty(value = "答案id")
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 试题id
     */
    @ApiModelProperty(value = "试题id")
    private String paperSubjectId;
    /**
     * 答案
     */
    @ApiModelProperty(value = "答案")
    private String answer;
    /**
     * 预留
     */
    @ApiModelProperty(value = "预留")
    private String field1;
    /**
     * 预留
     */
    @ApiModelProperty(value = "预留")
    private String field2;
    /**
     * 预留
     */
    @ApiModelProperty(value = "预留")
    private String field3;
}
