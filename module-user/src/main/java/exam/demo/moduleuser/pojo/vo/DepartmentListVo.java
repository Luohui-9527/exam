package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class DepartmentListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 6729949103076433176L;
    /**
     * 部门ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 公司ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门名
     */
    private String parentDepartment;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门等级
     */
    private String level;

    /**
     * 上级部门
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parentId;

    /**
     * 负责人
     */
    private String master;

    /**
     * 部门描述
     */
    private String descript;

    /**
     * 状态
     */
    private Byte status;

    public DepartmentListVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    @Override
    public String toString() {
        return "DepartmentListVo{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", name='" + name + '\'' +
                ", parentDepartment='" + parentDepartment + '\'' +
                ", mnemonicCode='" + mnemonicCode + '\'' +
                ", code='" + code + '\'' +
                ", level='" + level + '\'' +
                ", parentId=" + parentId +
                ", master='" + master + '\'' +
                ", descript='" + descript + '\'' +
                ", status=" + status +
                '}';
    }
}
