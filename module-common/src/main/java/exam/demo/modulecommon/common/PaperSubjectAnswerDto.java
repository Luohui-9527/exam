package exam.demo.modulecommon.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperSubjectAnswerDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String answer;
    private Byte rightAnswer;
}
