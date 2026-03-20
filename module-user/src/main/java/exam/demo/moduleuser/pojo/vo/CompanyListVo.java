package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class CompanyListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 3308191773763468129L;
    /**
     * 公司ID
     */
    private String id;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 公司名
     */
    private String name;

    /**
     * 公司编号
     */
    private String code;

    /**
     * 助记码
     */
    private String mnemonicCode;

    /**
     * 法人
     */
    private String master;

    /**
     * 税号
     */
    private String tax;

    /**
     * 传真
     */
    private String fax;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 所属机构
     */
    private String orgName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 网址
     */
    private String website;

    /**
     * 状态位
     */
    private Byte status;

}
