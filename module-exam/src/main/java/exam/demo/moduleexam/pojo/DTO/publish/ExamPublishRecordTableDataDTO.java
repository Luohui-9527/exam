package exam.demo.moduleexam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamPublishRecordTableDataDTO {
    private Long id;
    private Long publisher;
    private Date createdTime;
    private Date endTime;
    private Long planPepoleNum;
    private Long limitTime;
    private String title;
    private String descript;
    private Byte status;
    private Long version;
    private Long examSession;
    private Long publishTimes;
    private List<Long> examiners;
    private Long paperId;
}
