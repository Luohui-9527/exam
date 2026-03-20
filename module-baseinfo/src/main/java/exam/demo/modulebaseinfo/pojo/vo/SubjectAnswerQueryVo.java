package exam.demo.modulebaseinfo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案查询VO
 *
 * @author luohui
 */
@Data
public class SubjectAnswerQueryVo implements Serializable {

    private static final long serialVersionUID = 9123798642099490489L;

    private String subjectAnswerId;

    private String subjectId;

    /**
     * 答案
     */
    private String answer;

    public SubjectAnswerQueryVo() {
    }

}
