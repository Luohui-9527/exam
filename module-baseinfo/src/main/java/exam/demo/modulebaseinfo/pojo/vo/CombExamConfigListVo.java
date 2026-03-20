package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 组卷配置返回VO
 *
 * @author
 */
@Data
public class CombExamConfigListVo extends BaseListVo implements Serializable {

    private static final long serialVersionUID = -2229661452489156536L;

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
     * 更新人
     */
    private String updatedByName;

    /**
     * 更新人id
     */
    private String updatedBy;

    /**
     * 公司
     */
    private String company;

    /**
     * 公司id
     */
    private String companyId;


    public CombExamConfigListVo() {
    }

}
