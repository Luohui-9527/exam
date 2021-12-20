package exam.demo.modulecommon.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 组卷配置明细DTO
 *
 * @author luohui
 */
@Data
public class CombExamConfigItemDto implements Serializable {
    private static final long serialVersionUID = 6193674149029494679L;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectTypeId;

    /**
     * 题目类型
     */
    private String subjectType;

    /**
     * 组卷配置id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long combExamId;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 题目类别
     */
    private String category;

    /**
     * 题目数量
     */
    private Integer num;

    /**
     * 题目难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;

    /**
     * 题目难度
     */
    private String difficultyName;

    /**
     * 题目分数
     */
    private Double score;

    private Long version;

    /**
     * 标记新增明细
     */
    private Boolean save;

}
