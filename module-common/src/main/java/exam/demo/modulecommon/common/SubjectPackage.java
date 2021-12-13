package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectPackage {
    List<SubjectPackageDto> dtoList;
}
