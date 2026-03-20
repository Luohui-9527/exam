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
public class PositionItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -1472445396328905944L;
    /**
     * 职位ID
     */
    private String id;

    /**
     * 公司ID
     */
    @NotNull(message = "公司不能为空")
    private String companyId;

    /**
     * 职位名称
     */
    @NotNull(message = "职位不能为空")
    private String name;

    /**
     * 职位编号
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Byte status;

    public PositionItemVo() {
    }

}
