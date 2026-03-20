package exam.demo.modulepaper.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.util.Date;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Data
public class PaperQueryVo extends BaseQueryVo {
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 创建者
     */
    private String createdBy;
    /**
     * 难度
     */
    private String difficulty;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 截至时间
     */
    private Date end;

}
