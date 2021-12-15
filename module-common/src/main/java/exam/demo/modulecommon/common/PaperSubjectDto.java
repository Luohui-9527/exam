package exam.demo.modulecommon.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperSubjectDto {
    /**
     * 试题id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 试卷id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer paperId;
    /**
     * 题目
     */
    private String subject;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer categoryId;
    /**
     * 难度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;
    /**
     * 题目类型从基础数据中取
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectTypeId;
    /**
     * 是否为客观题
     */
    private Boolean objectiveSubject;
    /**
     * 分数
     */
    private Double score;
    private List<PaperSubjectAnswerDto> subjectAnswerVoList;
    /**
     * 标记是否为组卷服务传过去的原始题目，如果从基础数据服务中添加题目，则在接收对象时此数据不为9999
     */
    private Integer mark;

    private String categoryValue;
    private String difficultyValue;
    private String subjectTypeName;

}
