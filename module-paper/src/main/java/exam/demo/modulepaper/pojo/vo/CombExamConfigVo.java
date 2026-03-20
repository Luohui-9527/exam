package exam.demo.modulepaper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 组卷配置
 *
 * @author luohui
 * @version 1.0
 * @since 2019-08-29
 */
@Data
public class CombExamConfigVo {
    @NotBlank(message = "组卷配置id不能为空")
    private String id;
    /**
     * 组卷配置名
     */
    private String name;
    /**
     * 试卷类型
     */
    private String paperType;
    /**
     * 试卷难度
     */
    private String difficulty;

    /**
     * 状态位
     */
    @Max(2)
    private Byte status;

    /**
     * 备注
     */
    private String remark;

    public String companyId;

    /**
     * 通过id到数据字典中查询创建者
     */
    public String createdBy;
    /**
     * 修改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date updatedTime;
    /**
     * 版本，为Date.getTime()
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;

    public CombExamConfigVo() {
    }

}
