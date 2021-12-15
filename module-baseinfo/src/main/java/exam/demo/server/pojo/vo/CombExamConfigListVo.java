package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 组卷配置返回VO
 * @author
 */
public class CombExamConfigListVo extends BaseListVo implements Serializable {

    private static final long serialVersionUID = -2229661452489156536L;

    /**
     * 试卷难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;

    /**
     * 试卷难度
     */
    private String difficultyName;

    /**
     * 更新人
     */
    private String updatedByName;

    /**
     * 更新人id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer updatedBy;

    /**
     * 公司
     */
    private String company;

    /**
     * 公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;


    public CombExamConfigListVo() {
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "CombExamConfigListVO{" +
                "difficulty=" + difficulty +
                ", difficultyName='" + difficultyName + '\'' +
                ", updatedByName='" + updatedByName + '\'' +
                ", updatedBy=" + updatedBy +
                ", company='" + company + '\'' +
                ", companyId=" + companyId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", status=" + status +
                ", updatedTime=" + updatedTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
