package exam.demo.moduleexam.pojo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "exam_record")
public class ExamRecord {
    /**
     * 考试记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考试人员工号
     */
    @Column(name = "examiner_code")
    private String examinerCode;

    /**
     * 考试人员
     */
    private String examiner;

    /**
     * 手机号
     */
    private String tel;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 用户属性
     */
    @Column(name = "user_attribute")
    private Byte userAttribute;

    /**
     * 计划开始时间
     */
    @Column(name = "plan_start_time")
    private Date planStartTime;

    /**
     * 计划结束时间
     */
    @Column(name = "plan_end_time")
    private Date planEndTime;

    /**
     * 实际开始时间
     */
    @Column(name = "actual_start_time")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @Column(name = "actual_end_time")
    private Date actualEndTime;

    /**
     * 客观题得分
     */
    @Column(name = "objective_subject_score")
    private Double objectiveSubjectScore;

    /**
     * 主观题得分
     */
    @Column(name = "subjectvie_subject_score")
    private Double subjectvieSubjectScore;

    /**
     * 总分
     */
    private Double score;

    /**
     * 系统评价
     */
    @Column(name = "system_evaluate")
    private String systemEvaluate;

    /**
     * 状态
     */
    private Byte status;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 版本
     */
    private Long version;

    @Column(name = "exam_publish_record_id")
    private Integer examPublishRecordId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;
}