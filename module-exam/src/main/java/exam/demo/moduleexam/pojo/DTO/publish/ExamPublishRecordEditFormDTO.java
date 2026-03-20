package exam.demo.moduleexam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;

@Data
public class ExamPublishRecordEditFormDTO {
    private String id;
    private String descript;
    private Date startTime;
    private Date endTime;
    private Long examSession;
    private Long limitTime;
    private Long markingMode;
    private Date markingStopTime;
    private String paperId;
    private Long planPepoleNum;
    private String title;
}
