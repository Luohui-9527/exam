package exam.demo.moduleuser.pojo.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

public class UserAlloctionQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -4104146631752309115L;
    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 用户工号
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 公司Id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;
    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer orgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "UserAlloctionQuseryVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", companyId=" + companyId +
                ", orgId=" + orgId +
                '}';
    }
}
