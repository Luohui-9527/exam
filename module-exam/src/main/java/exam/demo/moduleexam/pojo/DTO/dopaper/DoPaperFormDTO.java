package exam.demo.moduleexam.pojo.DTO.dopaper;

import lombok.Data;

@Data
public class DoPaperFormDTO {
    private String standardAnswer;
    private String myAnswer;
    private Integer examRecordId;
    private Integer paperSubjectId;
    private String subjectType;
}
