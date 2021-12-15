package exam.demo.modulecommon.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 题目DTO
 *
 * @author luohui
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto extends BaseDataBaseDto implements Serializable {
    private static final long serialVersionUID = 6193674149029494679L;

    /**
     * 题目类别
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer categoryId;

    /**
     * 题目类别名字
     */
    private String categoryName;

    /**
     * 题目难度名
     */
    private String difficultyName;

    /**
     * 题型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectTypeId;
    /**
     * 题目类型名字
     */
    private String subjectTypeName;


    /**
     * 题目数量
     */
    private Integer num;

    /**
     * 题目分数
     */
    private Double score;

}
