
package exam.demo.moduleexam.pojo.DTO.publish;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class ExamPublishRecordQueryFormDTO {

    private List<String> examTimeRange;
    private List<String> publishTimeRange;
    private Integer publisher;
    private String title;

}
