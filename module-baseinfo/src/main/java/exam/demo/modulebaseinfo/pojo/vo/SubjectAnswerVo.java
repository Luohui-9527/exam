package exam.demo.modulebaseinfo.pojo.vo;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 题目答案VO
 *
 * @author luohui
 */
@Data
public class SubjectAnswerVo implements Serializable {

    private static final long serialVersionUID = 3417000771878278931L;

    @Id
    private String id;

    private String subjectId;

    @NotBlank(message = "答案不能为空！")
    private String answer;

    @NotNull(message = "答案是否正确不能为空！")
    private Byte rightAnswer;

}
