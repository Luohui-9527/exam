package exam.demo.moduleuser.pojo.vo;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class DepartmentItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -6129972664056962573L;
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
    @NotNull(message = "部门名称不能为空")
    private String name;

    /**
     * 助记码
     */
    @NotNull(message = "助记码不能为空")
    private String mnemonicCode;

    /**
     * 部门编号
     */
    @NotNull(message = "部门编号不能为空")
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
    @NotNull(message = "负责人不能为空")
    private String master;

    /**
     * 部门描述
     */
    private String descript;

    /**
     * 状态
     */
    private Byte status;

    public DepartmentItemVo() {
    }

}

