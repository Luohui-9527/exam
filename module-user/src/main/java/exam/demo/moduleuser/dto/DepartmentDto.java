package exam.demo.moduleuser.dto;


import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * t_department
 *
 * @author
 */
@Data
public class DepartmentDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 8187461747461618819L;
    /**
     * 部门ID
     */
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 部门级别
     */
    private String level;

    /**
     * 上级部门
     */
    private String parentId;

    /**
     * 上级部门名
     */
    private String parentDepartment;

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

    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private String judgeId;

    public DepartmentDto() {
    }
}
