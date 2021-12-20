package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class CompanyItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -4102804158288838906L;
    /**
     * 公司ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 组织机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgId;

    /**
     * 公司名
     */
    @NotNull(message = "公司名不能为空")
    private String name;

    /**
     * 公司编号
     */
    @NotNull(message = "公司编号不能为空")
    private String code;

    /**
     * 助记码
     */
    @NotNull(message = "助记码不能为空")
    private String mnemonicCode;

    /**
     * 法人
     */
    @NotNull(message = "法人不能为空")
    private String master;

    /**
     * 税号
     */
    @NotNull(message = "税号不能为空")
    private String tax;

    /**
     * 传真
     */
    private String fax;

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
     * 邮编
     */
    @NotNull(message = "邮编不能为空")
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
