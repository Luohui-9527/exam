package exam.demo.moduleexam.pojo.DTO.grade;

import lombok.Data;

/**
 * @author luohui
 */
@Data
public class MarkingAnswerDTO {
    /**
     * 客观题
     */
    private Boolean objectiveSubject;
    /**
     * 分数
     */
    private Double score;

    /**
     * 评估
     */
    private String evaluate;
    /**
     * 试题id
     */
    private Integer paperSubjectId;
}
