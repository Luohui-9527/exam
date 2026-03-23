package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目查询结果VO
 * 用于返回查询结果
 *
 * @author luohui
 */
@Data
public class SubjectQueryResultVo implements Serializable {

    private static final long serialVersionUID = 1902508370783136321L;

    public SubjectQueryResultVo() {
    }

    /**
     * 题目id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    /**
     * 题目
     */
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String difficulty;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String subjectTypeId;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String categoryId;

    /**
     * 非表对应字段
     * 题目类型名字
     */
    private String subjectTypeName;

    /**
     * 非表对应字段
     * 题目类别名字
     */
    private String categoryName;

    /**
     * 非表对应字段
     * 题目难度名字
     */
    private String difficultyName;

    /**
     * 修改日期
     */
    private Date updatedTime;

}
