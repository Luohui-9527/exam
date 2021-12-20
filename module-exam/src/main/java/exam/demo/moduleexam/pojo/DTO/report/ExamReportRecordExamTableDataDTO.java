package exam.demo.moduleexam.pojo.DTO.report;

import lombok.Data;

import java.util.Date;

@Data
public class ExamReportRecordExamTableDataDTO {
    private Long id;
    private Integer actualPepoleNum;
    private Date endTime;
    private Integer examSession;
    private Integer planPepoleNum;
    private String title;

}
