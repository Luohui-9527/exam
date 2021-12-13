package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2019-09-16
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPackageDto {
    SubjectDto subjectDTO;
    List<SubjectAnswerDto> subjectAnswerDtoList;
}
