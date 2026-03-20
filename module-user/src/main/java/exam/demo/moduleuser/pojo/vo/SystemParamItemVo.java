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
public class SystemParamItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 7757535022165772960L;
    /**
     * 系统参数ID
     */
    private String id;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 参数类型
     */
    @NotNull(message = "参数类型不能为空")
    private Long paramType;

    /**
     * 参数项
     */
    @NotNull(message = "参数项不能为空")
    private String param;

    /**
     * 参数值
     */
    @NotNull(message = "参数值不能为空")
    private String value;

    /**
     * 状态位
     */
    private Byte status;

}
