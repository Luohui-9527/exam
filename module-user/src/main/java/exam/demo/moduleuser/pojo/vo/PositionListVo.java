package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class PositionListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -4606367687217083037L;
    /**
     * 职位ID
     */
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 职位名称
     */
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

    public PositionListVo() {
    }


}
