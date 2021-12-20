package exam.demo.modulebaseinfo.dto;

import exam.demo.modulecommon.common.SubjectPackageDto;
import lombok.Data;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2019-09-16
 */
@Data
public class SubjectPackageList {

    List<SubjectPackageDto> packageDTOList;
}
