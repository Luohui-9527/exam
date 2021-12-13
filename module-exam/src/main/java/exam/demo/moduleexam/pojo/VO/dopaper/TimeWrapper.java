package exam.demo.moduleexam.pojo.VO.dopaper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-05-27
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeWrapper {
    int hour;
    int min;
}
