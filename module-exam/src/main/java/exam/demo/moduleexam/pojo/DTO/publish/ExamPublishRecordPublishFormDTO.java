package exam.demo.moduleexam.pojo.DTO.publish;

import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamPublishRecordPublishFormDTO extends BaseDto {
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
    private List<String> examinersId;
    private String publisher;
}
