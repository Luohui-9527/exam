package exam.demo.modulepaper.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Data
@Accessors(chain = true)
public class PaperSubjectAnswer extends Model<PaperSubjectAnswer> {
    /**
     * 答案id
     */
    private Integer id;


    /**
     * 试题id
     */
    private Integer paperSubjectId;

    /**
     * 答案
     */
    private String answer;

    /**
     * 是否为正确答案
     */
    private Byte rightAnswer;

    /**
     * 预留
     */
    private String field1;

    /**
     * 预留
     */
    private String field2;

    /**
     * 预留
     */
    private String field3;

    @Override
    protected Serializable pkVal() {
        return id;
    }

    public static final String ID = "id";
    public static final String PAPER_SUBJECT_ID = "paper_subject_id";
    public static final String ANSWER  = "answer";
    public static final String RIGHT_ANSWER = "right_answer";
    public static final String FIELD1 = "field1";
    public static final String FIELD2 = "field2";
    public static final String FIELD3 = "field3";
}
