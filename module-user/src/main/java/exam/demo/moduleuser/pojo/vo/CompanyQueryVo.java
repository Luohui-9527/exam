package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class CompanyQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = 2356517833861707124L;
    /**
     * 所属机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer orgId;
    /**
     * 公司名
     */
    private String name;
    /**
     * 所属机构
     */
    private String orgName;

    public CompanyQueryVo() {
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "CompanyQueryVo{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
