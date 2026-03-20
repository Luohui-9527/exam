package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 组卷配置明细返回VO
 *
 * @author
 */
@Data
public class CombExamConfigItemListVo implements Serializable {

    private static final long serialVersionUID = -1480769799480328892L;

    /**
     * 组卷配置明细id
     */
    @Id
    private String id;

    /**
     * 题目类型id
     */
    private String subjectTypeId;

    /**
     * 题目类型
     */
    private String subjectType;

    /**
     * 组卷配置id
     */
    private String combExamId;

    /**
     * 题目类别id
     */
    private String categoryId;

    /**
     * 题目类别
     */
    private String category;

    /**
     * 题目数量
     */
    private Long num;

    /**
     * 题目难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String difficulty;

    /**
     * 题目难度
     */
    private String difficultyName;

    /**
     * 题目分数
     */
    private Double score;

    public CombExamConfigItemListVo() {
    }

}
