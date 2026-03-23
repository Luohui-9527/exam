package exam.demo.modulepaper.pojo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 试卷试题表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("paper_subject")
public class PaperSubject implements Serializable {

    /**
     * 试题id
     */
    @ApiModelProperty(value = "试题id")
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 试卷id
     */
    @ApiModelProperty(value = "试卷id")
    private String paperId;
    /**
     * 题目
     */
    @ApiModelProperty(value = "题目")
    private String subjectName;
    /**
     * 题目
     */
    @ApiModelProperty(value = "题目")
    private String subjectId;
    /**
     * sb
     */
    @ApiModelProperty(value = "sb")
    private String categoryId;
    /**
     * 题目类型从基础数据中取
     */
    @ApiModelProperty(value = "题目类型从基础数据中取")
    private String subjectTypeId;
    /**
     * 难度
     */
    @ApiModelProperty(value = "难度")
    private String difficulty;
    /**
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Double score;
}
