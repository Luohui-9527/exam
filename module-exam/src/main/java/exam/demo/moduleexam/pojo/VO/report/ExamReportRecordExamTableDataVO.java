package exam.demo.moduleexam.pojo.VO.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamReportRecordExamTableDataVO implements Serializable {
    private static final long serialVersionUID = 4496661543421891194L;
    /**
     * 考试记录id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 实际考试人数
     */
    private Long actualPepoleNum;
    /**
     * 考试结束时间
     */
    private String endTime;
    /**
     * 考试场次
     */
    private Long examSession;
    /**
     * 计划考试人数
     */
    private Long planPepoleNum;
    /**
     * 考试标题
     */
    private String title;
}
