package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目查询VO
 *
 * @author luohui
 */
@Data
@ToString(callSuper = true)
public class SubjectQueryVo extends BaseQueryVo implements Serializable {

    private static final long serialVersionUID = 1902508370783136321L;

    /**
     * 题目id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 题目
     */
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long difficulty;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectTypeId;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;


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
    public Date updatedTime;

    /**
     * 题目类型属性列
     */
    private String attribute;

    /**
     * 状态位
     */
    private Byte status;

    private String remark;

}
