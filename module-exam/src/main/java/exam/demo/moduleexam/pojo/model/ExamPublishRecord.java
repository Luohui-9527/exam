package exam.demo.moduleexam.pojo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "exam_publish_record")
public class ExamPublishRecord {
    /**
     * 考试发布记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 考试标题
     */
    private String title;

    /**
     * 考试场次
     */
    @Column(name = "exam_session")
    private Long examSession;

    /**
     * 发布人
     */
    private Long publisher;

    /**
     * 考试开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 考试结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 计划人数
     */
    @Column(name = "plan_pepole_num")
    private Integer planPepoleNum;

    /**
     * 考试时长
     */
    @Column(name = "limit_time")
    private Integer limitTime;

    /**
     * 考试说明
     */
    private String descript;

    /**
     * 阅卷方式
     */
    @Column(name = "marking_mode")
    private Integer markingMode;

    /**
     * 阅卷停止时间
     */
    @Column(name = "marking_stop_time")
    private Date markingStopTime;

    /**
     * 二维码链接
     */
    @Column(name = "qrcode_url")
    private String qrcodeUrl;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private Long companyId;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private Long updatedBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 版本
     */
    private Long version;

    /**
     * 预留字段1
     */
    private String field1;

    /**
     * 预留字段2
     */
    private String field2;

    /**
     * 试卷ID
     */
    @Column(name = "paper_id")
    private Long paperId;

    /**
     * examRecords 关联
     */
    List<ExamRecord> examRecords;

    public List<ExamRecord> getExamRecords() {
        return examRecords;
    }
}