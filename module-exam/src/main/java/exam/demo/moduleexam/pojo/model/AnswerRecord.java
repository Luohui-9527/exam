package exam.demo.moduleexam.pojo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "answer_record")
public class AnswerRecord {
    /**
     * 答卷明细ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 我的答案
     */
    @Column(name = "my_answer")
    private String myAnswer;

    /**
     * 标准答案
     */
    @Column(name = "standard_answer")
    private String standardAnswer;

    /**
     * 得分
     */
    private Double score;

    /**
     * 评价
     */
    private String evaluate;

    /**
     * 题目ID
     */
    private String paperSubjectId;

    private String examRecordId;

}