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
public class OrganizationItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -3299830833528301501L;
    /**
     * 组织机构ID
     */
    private String id;

    /**
     * 机构名
     */
    @NotNull(message = "机构名不能为空")
    private String name;

    /**
     * 机构代码
     */
    @NotNull(message = "机构代码不能为空")
    private String code;

    /**
     * 负责人
     */
    @NotNull(message = "负责人不能为空")
    private String master;

    /**
     * 电话
     */
    @NotNull(message = "电话不能为空")
    private String tel;

    /**
     * 地址
     */
    @NotNull(message = "地址不能为空")
    private String address;

    /**
     * 状态位
     */
    private Byte status;


    public OrganizationItemVo() {
    }

}
