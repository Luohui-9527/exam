package exam.demo.moduleexam.pojo.DTO.grade;

import lombok.Data;

import java.util.List;

@Data
public class ExamGradeRecordQueryFormDTO {

    private List<String> endTimeRange;
    private Byte status;
    private Long examSession;
    private Long userId;

}
