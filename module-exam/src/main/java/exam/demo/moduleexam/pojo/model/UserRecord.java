package exam.demo.moduleexam.pojo.model;

import javax.persistence.*;

@Table(name = "user_record")
public class UserRecord {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考试发布记录
     */
    @Column(name = "exam_publish_record_id")
    private Integer examPublishRecordId;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取考试发布记录
     *
     * @return exam_publish_record_id - 考试发布记录
     */
    public Integer getExamPublishRecordId() {
        return examPublishRecordId;
    }

    /**
     * 设置考试发布记录
     *
     * @param examPublishRecordId 考试发布记录
     */
    public void setExamPublishRecordId(Integer examPublishRecordId) {
        this.examPublishRecordId = examPublishRecordId;
    }
}