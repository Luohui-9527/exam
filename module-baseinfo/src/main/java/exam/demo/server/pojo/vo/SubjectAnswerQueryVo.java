package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 题目答案查询VO
 *
 * @author luohui
 */
public class SubjectAnswerQueryVo implements Serializable {

    private static final long serialVersionUID = 9123798642099490489L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectAnswerId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectId;

    /**
     * 答案
     */
    private String answer;

    public SubjectAnswerQueryVo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSubjectAnswerId() {
        return subjectAnswerId;
    }

    public void setSubjectAnswerId(Integer subjectAnswerId) {
        this.subjectAnswerId = subjectAnswerId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SubjectAnswerQueryVo{" +
                "subjectAnswerId=" + subjectAnswerId +
                ", subjectId=" + subjectId +
                ", answer='" + answer + '\'' +
                '}';
    }
}
