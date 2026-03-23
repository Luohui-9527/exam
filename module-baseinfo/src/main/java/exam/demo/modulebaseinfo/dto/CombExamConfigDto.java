package exam.demo.modulebaseinfo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseDataBaseDto;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import lombok.Data;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.List;

/**
 * 组卷配置DTO
 *
 * @author luohui
 */
@Data
public class CombExamConfigDto extends BaseDataBaseDto implements Serializable {
    private static final long serialVersionUID = 6193674149029494679L;

    /**
     * 组卷配置名
     */
    private String name;

    /**
     * 试卷难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String difficulty;

    /**
     * 试卷难度
     */
    private String difficultyName;

    /**
     * 删除id
     */
    private List<Long> deleteIds;

    /**
     * 组卷配置明细DTO
     */
    private List<CombExamConfigItemDto> combExamConfigItemDtoList;

    /**
     * 状态位
     */
    @Max(2)
    private Byte status;

    private String remark;

    private String updatedByName;

    private String company;

    public CombExamConfigDto() {
    }

}
