package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 试卷Id和试卷名称
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperIdWithName {
    String id;
    String paperName;
}
