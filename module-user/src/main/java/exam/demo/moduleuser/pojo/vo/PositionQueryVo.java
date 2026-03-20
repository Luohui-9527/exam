package exam.demo.moduleuser.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class PositionQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -5311406730359216485L;
    /**
     * 职位ID
     */
    private String id;
    /**
     * 职位名
     */
    private String name;

    public PositionQueryVo() {
    }

}
