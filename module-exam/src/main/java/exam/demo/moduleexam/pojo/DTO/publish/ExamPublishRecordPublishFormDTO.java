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
    private Integer examSession;
    private Integer limitTime;
    private Integer markingMode;
    private Date markingStopTime;
    private Integer paperId;
    private Integer planPepoleNum;
    private String title;
    private List<Integer> examinersId;
    private Integer publisher;
}
