package exam.demo.modulepaper.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizedCombExamConfigVo {
    private String name;
    private String status;
    private Integer difficulty;
    private String remark;
    private Integer paperType;
    private List<CustomizedCombExamConfigItemVo> combExamConfigItemVOs;
}
