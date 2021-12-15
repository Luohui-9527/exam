
package exam.demo.moduleexam.pojo.DTO.answersheet;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordQueryFormDTO {

    private Integer examSession;
    private List<String> examTimeRange;
    private Integer publisher;
    private String title;

}
