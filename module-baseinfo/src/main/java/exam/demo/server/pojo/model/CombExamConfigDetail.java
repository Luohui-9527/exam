package exam.demo.server.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CombExamConfigDetail extends Model<CombExamConfigDetail> {

    private static final long serialVersionUID = 6160710406555796155L;
    /**
     * 组卷配置明细id
     */
    private Integer id;

    /**
     * 题型ID
     */
    private Integer subjectTypeId;

    /**
     * 题型名
     */
    @TableField(exist = false)
    private String subjectType;

    /**
     * 组卷配置ID
     */
    private Integer combExamConfigId;

    /**
     * 题目类别ID
     */
    private Integer categoryId;

    /**
     * 题目类别名字
     */
    @TableField(exist = false)
    private String category;

    /**
     * 题目数量
     */
    private Integer num;

    /**
     * 题目难度
     */
    private Integer difficulty;

    /**
     * 题目难度名
     */
    @TableField(exist = false)
    private String difficultyName;

    /**
     * 题目分值
     */
    private Double score;

    @Override
    protected Serializable pkVal() {
        return id;
    }

    public static final String ID = "id";
    public static final String SUBJECT_TYPE_ID = "subject_type_id";
    public static final String COMB_EXAM_CONFIG_ID = "comb_exam_config_id";
    public static final String CATEGORY_ID = "category_id";
    public static final String NUM = "num";
    public static final String DIFFICULTY = "difficulty";
    public static final String SCORE = "score";
}
