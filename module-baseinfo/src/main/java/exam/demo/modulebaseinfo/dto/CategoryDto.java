package exam.demo.modulebaseinfo.dto;

import exam.demo.modulecommon.common.BaseDataBaseDto;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * 题目类别DTO
 *
 * @author luohui
 */
@Data
public class CategoryDto extends BaseDataBaseDto implements Serializable {
    private static final long serialVersionUID = 1645491693425153066L;

    /**
     * 题目类别
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父亲节点id
     */
    private Long parentId;

    /**
     * 父亲名
     */
    private String parentName;

    /**
     * 状态位
     */
    @Max(2)
    private Byte status;


}
