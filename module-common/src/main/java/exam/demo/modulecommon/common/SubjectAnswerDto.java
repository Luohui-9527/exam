package exam.demo.modulecommon.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectAnswerDto implements Serializable {

    private static final long serialVersionUID = -1859606015049770782L;

    private Long subjectAnswerId;

    private Long subjectId;

    private String answer;

    private byte rightAnswer;

    private Object field1;

    private Object field2;

    private Object field3;
}
