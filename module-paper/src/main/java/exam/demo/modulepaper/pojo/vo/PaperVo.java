package exam.demo.modulepaper.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.util.Date;

/**
 * 试卷
 *
 * @author luohui
 * @version 1.0
 * @since 2019-08-19
 */
@Data
public class PaperVo extends BaseQueryVo {
    /**
     * 试卷id
     */
    private String id;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试卷类型id
     */
    private String paperType;
    /**
     * 试卷类型，通过id从数据字典中查询
     */
    private String paperTypeValue;
    /**
     * 难度id
     */
    private String difficulty;
    /**
     * 难度，通过id从数据字典中查询
     */
    private String difficultyValue;
    /**
     * 组卷日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date combExamTime;
    /**
     * 试卷分数
     */
    private Double score;
    /**
     * 试卷描述
     */
    private String description;
    /**
     * 试卷是否为模板
     */
    private Byte template;
    /**
     * 下载次数
     */
    private Long downloadTimes;
    /**
     * 发布次数
     */
    private Long publishTimes;
    /**
     * 试卷状态
     */
    private Byte status;
    /**
     * 创建者
     */
    private String paperCreator;
    /**
     * 创建时间
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;
    /**
     * 公司id
     */
    private String companyId;

    private String companyValue;
    private String updatedBy;

    private String updatedByValue;
    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updatedTime;

    public PaperVo() {
    }

}
