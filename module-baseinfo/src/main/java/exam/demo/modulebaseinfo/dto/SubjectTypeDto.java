package exam.demo.modulebaseinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseDataBaseDto;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;

/**
 * 题目类型DTO
 *
 * @author luohui
 */
@Data
public class SubjectTypeDto extends BaseDataBaseDto implements Serializable {
    private static final long serialVersionUID = 6193674149029494679L;

    /**
     * 题目类型属性列
     */
    private String attribute;

    /**
     * 题目类型名
     */
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long subjectTypeId;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 状态位
     */
    @Max(2)
    private Byte status;

    /**
     * 备注
     */
    private String remark;

}
