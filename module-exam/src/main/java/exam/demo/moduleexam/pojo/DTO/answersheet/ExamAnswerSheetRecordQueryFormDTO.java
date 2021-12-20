
package exam.demo.moduleexam.pojo.DTO.answersheet;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordQueryFormDTO {

    private Long examSession;
    private List<String> examTimeRange;
    private Long publisher;
    private String title;

}
