package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 题目答案VO
 *
 * @author luohui
 */
public class SubjectAnswerVo implements Serializable {

    private static final long serialVersionUID = 3417000771878278931L;

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectId;

    @NotBlank(message = "答案不能为空！")
    private String answer;

    @NotNull(message = "答案是否正确不能为空！")
    private Byte rightAnswer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Byte rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "SubjectAnswerVO{" +
                "id=" + id +
                ", subjectId=" + subjectId +
                ", answer='" + answer + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
