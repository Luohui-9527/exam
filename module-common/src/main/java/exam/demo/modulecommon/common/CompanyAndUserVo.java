package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户Id,名称
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAndUserVo {
    String company;
    String user;
}
