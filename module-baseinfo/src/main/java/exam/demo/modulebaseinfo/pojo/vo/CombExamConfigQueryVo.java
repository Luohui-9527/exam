package exam.demo.modulebaseinfo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 组卷配置查询VO
 *
 * @author
 */
@Data
public class CombExamConfigQueryVo implements Serializable {

    private static final long serialVersionUID = 697915909468078349L;

    /**
     * 组卷配置id
     */
    private String id;

    /**
     * 组卷配置名
     */
    private String name;

    public CombExamConfigQueryVo() {
    }

}
