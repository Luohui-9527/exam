package exam.demo.moduleuser.dto;

import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * t_position
 *
 * @author
 */
@Data
public class PositionDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 3628861049600787289L;
    /**
     * 职位ID
     */
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

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

    /**
     * 所属公司
     */
    private String companyName;
    /**
     * 当前请求页
     */
    private int currentPage;
    /**
     * 页面显示条数
     */
    private int pageSize;

    private String judgeId;

}
