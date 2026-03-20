package exam.demo.modulecommon.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
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
public class BaseDataDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, String> baseInfoMap;
    private String id;
    private String name;
    private String code;

    private Long pageNum;
    private Long pageSize;
    private List<?> list;
}
