package exam.demo.modulepaper.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.util.Date;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Data
public class PaperQueryVo extends BaseQueryVo {
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 创建者
     */
    private String createdBy;
    /**
     * 难度
     */
    private Long difficulty;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 截至时间
     */
    private Date end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
