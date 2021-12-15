package exam.demo.modulecommon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Data
public class PaperDetail {
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer createdBy;
    private String paperCreator;
    /**
     * 试卷类型
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer category;
    private String categoryValue;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;
    private String difficultyValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date combExamTime;
    private Double score;
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;
    private List<PaperSubjectDto> currentPaperSubjectDtoList;
    /**
     * 在前端删除的试题的Id
     */
    private List<Integer> deletedId;
}
