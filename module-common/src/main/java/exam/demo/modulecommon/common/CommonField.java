package exam.demo.modulecommon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Id;
import java.util.Date;

/**
 * 表中共有字段
 * @author luohui
 * @version 1.0
 * @since 2019-08-12
 */
public class CommonField {

    /**
     * 雪花算法生成Id
     */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    public Long id;
    /**
     * 机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long orgId;
    /**
     * 机构下公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long companyId;
    /**
     * 通过id到数据字典中查询创建者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long createdBy;
    /**
     * 创建日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    public Date createdTime;
    /**
     * 通过id到数据字典中查询修改者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long updatedBy;
    /**
     * 修改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    public Date updatedTime;
    /**
     * 版本，为Date.getTime()
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;

    public CommonField() {
    }

    public CommonField(Long id, Long orgId, Long companyId, Long createdBy, Date createdTime, Long updatedBy, Date updatedTime, Long version) {
        this.id = id;
        this.orgId = orgId;
        this.companyId = companyId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CommonField{" +
                "id=" + id +
                ", orgId=" + orgId +
                ", companyId=" + companyId +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", updatedBy=" + updatedBy +
                ", updatedTime=" + updatedTime +
                ", version=" + version +
                '}';
    }
}
