package exam.demo.modulepaper.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-19
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyPaperDto {
    private String id;
    private String name;
    private String paperCreator;
    private String category;
    private String categoryValue;
    @JsonSerialize(using = ToStringSerializer.class)
    private String difficulty;
    private String difficultyValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date combExamTime;
    private Double score;
    private String description;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;
    private List<ModifyPaperSubjectDto> currentPaperSubjectDtoList;
    private List<String> deletedId;
}
