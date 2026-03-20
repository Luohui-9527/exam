package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class DepartmentListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 6729949103076433176L;
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
    private String parentId;

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

}
