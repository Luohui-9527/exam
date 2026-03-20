package exam.demo.modulebaseinfo.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 题目查询VO
 * 用于接收查询参数
 *
 * @author luohui
 */
@Data
public class SubjectQueryVo extends BaseQueryVo implements Serializable {

    private static final long serialVersionUID = 1902508370783136321L;

    /**
     * 题目
     */
    private String name;

    /**
     * 题目类型id
     */
    private String difficulty;

    /**
     * 题目类型id
     */
    private String subjectTypeId;

    /**
     * 题目类别id
     */
    private String categoryId;

}
