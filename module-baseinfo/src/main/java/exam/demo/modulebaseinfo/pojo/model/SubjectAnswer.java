package exam.demo.modulebaseinfo.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubjectAnswer extends Model<SubjectAnswer> {

    private static final long serialVersionUID = -2235692655549688216L;
    /**
     * 雪花算法生成Id
     */
    private Long id;

    private Long subjectId;

    private String answer;

    private Object field1;

    private Object field2;

    private Object field3;

}
