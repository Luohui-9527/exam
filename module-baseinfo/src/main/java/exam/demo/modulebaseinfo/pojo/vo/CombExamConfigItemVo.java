package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 组卷配置明细VO
 *
 * @author
 */
@Data
public class CombExamConfigItemVo implements Serializable {

    private static final long serialVersionUID = 7029875785512079058L;


    /**
     * 组卷配置明细id
     */
    private String id;

    /**
     * 题目类型id
     */
    @NotNull(message = "题目类型不能为空！")
    private String subjectTypeId;

    /**
     * 组卷配置id
     */
    private String combExamId;

    /**
     * 题目类别id
     */
    @NotNull(message = "题目类别不能为空！")
    private String categoryId;

    /**
     * 题目数量
     */
    private Long num;

    /**
     * 题目难度
     */
    @NotNull(message = "题目难度不能为空！")
    @JsonSerialize(using = ToStringSerializer.class)
    private String difficulty;

    /**
     * 题目分数
     */
    @Min(0)
    @NotNull(message = "题目分数不能为空！")
    private Double score;

    /**
     * 标记新增组卷配置明细
     */
    private Boolean save;

    public CombExamConfigItemVo() {
    }
}
