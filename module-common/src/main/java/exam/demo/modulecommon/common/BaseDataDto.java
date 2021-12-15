package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 与基础数据服务交互
 *
 * @author luohui
 * @version 1.0
 * @since 2019-09-27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDataDto {
    private Map<Integer, String> baseInfoMap;
}

