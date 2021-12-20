package exam.demo.moduleexam.pojo.VO.answersheet;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ExamAnswerSheetRecordQueryFormVO implements Serializable {
    private static final long serialVersionUID = 198994862820429412L;
    /**
     * 考试场次
     */
    private Long examSession;

    /**
     * 考试时间段
     */
    private List<Date> examTimeRange;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 考试标题
     */
    private String title;
    private Long currentPage;
}
