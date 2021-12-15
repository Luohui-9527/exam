package exam.demo.modulepaper.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomizedCombExamConfigItemVo {
    /**
     * 试题类别id 如java C
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer categoryId;
    /**
     * 试题类型id 如选择填空
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectTypeId;
    /**
     * 试题数量
     */
    private Integer num;
    /**
     * 试题难度
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;
    /**
     * 每道试题分数
     */
    private Double score;
}
