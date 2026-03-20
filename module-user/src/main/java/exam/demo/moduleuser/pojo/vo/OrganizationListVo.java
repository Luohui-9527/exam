package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class OrganizationListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -5966561480948201344L;
    /**
     * 组织机构ID
     */
    private String id;

    /**
     * 机构名
     */
    private String name;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态位
     */
    private Byte status;


    public OrganizationListVo() {
    }

}
