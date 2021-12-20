package exam.demo.moduleexam.pojo.VO.report;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamReportRecordDetailTableDataVO implements Serializable {
    private static final long serialVersionUID = -2440694185496671412L;
    /**
     * 考生姓名
     */
    private String examiner;
    /**
     * 性别
     */
    private Byte sex;
    /**
     * 考试标题
     */
    private String title;
    /**
     * 客观题分数
     */
    private Double objectiveSubjectScore;
    /**
     * 主观题分数
     */
    private Double subjectvieSubjectScore;
    /**
     * 总分
     */
    private Double score;
    /**
     * 排名 TODO 待生成
     */
    private Long ranking;
    /**
     * 考试耗时
     */
    private String examCostTime;
    /**
     * 能力标签
     */
    private String abilityLabel;


}
