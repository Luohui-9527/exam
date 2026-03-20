package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 题目VO
 *
 * @Date: 2019/9/5
 * @Version: 1.0
 * @Maintenance Records:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectVo extends BaseItemVo implements Serializable {

    private static final long serialVersionUID = 8413924345677180367L;

    /**
     * 题目
     */
    @NotBlank(message = "题目内容不能为空！")
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题型不能为空！")
    private String subjectTypeId;

    /**
     * 题目类型名
     */
    private String subjectTypeName;

    /**
     * 难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题目难度不能为空！")
    private String difficulty;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题目类别不能为空！")
    private String categoryId;

    /**
     * 题目类别名
     */
    private String categoryName;

    @Valid
    private List<SubjectAnswerVo> subjectAnswerVoList;

    public SubjectVo() {
    }

}
