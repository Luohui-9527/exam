
package exam.demo.moduleexam.pojo.DTO.answersheet;

import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordTableDataDTO {
    private Long id;
    private Date actualEndTime;
    private Date actualStartTime;
    private Date endTime;
    private Long examSession;
    private Long publisher;
    private String examiner;
    private Double objectiveSubjectScore;
    private Double subjectvieSubjectScore;
    private String tel;
    private String title;
    private Long paperId;
}
