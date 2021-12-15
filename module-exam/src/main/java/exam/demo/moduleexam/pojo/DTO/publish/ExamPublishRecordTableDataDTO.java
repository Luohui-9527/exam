package exam.demo.moduleexam.pojo.DTO.publish;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExamPublishRecordTableDataDTO {
    private Integer id;
    private Integer publisher;
    private Date createdTime;
    private Date endTime;
    private Integer planPepoleNum;
    private Integer limitTime;
    private String title;
    private String descript;
    private Byte status;
    private Long version;
    private Integer examSession;
    private Integer publishTimes;
    private List<Integer> examiners;
    private Integer paperId;
}
