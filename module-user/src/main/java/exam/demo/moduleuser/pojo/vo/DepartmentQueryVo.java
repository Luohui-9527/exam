package exam.demo.moduleuser.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class DepartmentQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -7089081877518720169L;
    /**
     * 部门id
     */
    private String id;
    /**
     * 部门名
     */
    @NotNull(message = "部门名不能为空")
    private String name;
    /**
     * 部门等级
     */
    @NotNull(message = "部门等级不能为空")
    private String level;

    public DepartmentQueryVo() {
    }

}
