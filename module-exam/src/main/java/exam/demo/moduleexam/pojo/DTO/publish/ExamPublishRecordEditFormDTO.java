package exam.demo.moduleexam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;

@Data
public class ExamPublishRecordEditFormDTO {
    private Integer id;
    private String descript;
    private Date startTime;
    private Date endTime;
    private Integer examSession;
    private Integer limitTime;
    private Integer markingMode;
    private Date markingStopTime;
    private Integer paperId;
    private Integer planPepoleNum;
    private String title;
}
