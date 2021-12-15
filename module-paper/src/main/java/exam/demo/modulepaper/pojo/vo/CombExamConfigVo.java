package exam.demo.modulepaper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 组卷配置
 *
 * @author luohui
 * @version 1.0
 * @since 2019-08-29
 */
public class CombExamConfigVo {
    @NotBlank(message = "组卷配置id不能为空")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 组卷配置名
     */
    private String name;
    /**
     * 试卷类型
     */
    private Integer paperType;
    /**
     * 试卷难度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;

    /**
     * 状态位
     */
    @Max(2)
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    @JsonSerialize(using = ToStringSerializer.class)
    public Integer companyId;

    /**
     * 通过id到数据字典中查询创建者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Integer createdBy;
    /**
     * 修改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date updatedTime;
    /**
     * 版本，为Date.getTime()
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;

    public CombExamConfigVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPaperType() {
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CombExamConfigVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paperType=" + paperType +
                ", difficulty=" + difficulty +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", companyId=" + companyId +
                ", createdBy=" + createdBy +
                ", updatedTime=" + updatedTime +
                ", version=" + version +
                '}';
    }
}
