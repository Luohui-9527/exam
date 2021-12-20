package exam.demo.moduleexam.pojo.VO.report;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ExamReportRecordQueryFormVO implements Serializable {
    private static final long serialVersionUID = 3167369384538978797L;
    /**
     * 考试场次
     */
    private Long examSession;
    /**
     * 考试时间段
     */
    private List<Date> examTimeRange;
    /**
     * 考试发布人
     */
    private String publisher;
    /**
     * 考试标题
     */
    private String title;
    private Long currentPage;
}
