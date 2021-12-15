
package exam.demo.moduleexam.pojo.DTO.answersheet;

import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class ExamAnswerSheetRecordTableDataDTO {
    private Integer id;
    private Date actualEndTime;
    private Date actualStartTime;
    private Date endTime;
    private Integer examSession;
    private Integer publisher;
    private String examiner;
    private Double objectiveSubjectScore;
    private Double subjectvieSubjectScore;
    private String tel;
    private String title;
    private Integer paperId;
}
