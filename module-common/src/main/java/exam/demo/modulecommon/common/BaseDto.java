package exam.demo.modulecommon.common;

import lombok.Data;

/**
 * DTO基类，token属性便于切面提取公共字段
 *
 * @author luohui
 * @version 1.0
 * @since 2019-08-23
 */
@Data
public class BaseDto extends CommonField {
    /**
     * 当前前端传来的DTO里的版本信息，在更新和删除时需要和数据库中version进行比较
     */
    private Long oldVersion;
}
