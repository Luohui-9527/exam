package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 题目类别VO
 *
 * @author
 */
@Data
public class CategoryVo extends BaseItemVo implements Serializable {

    private static final long serialVersionUID = -700878102267633514L;

    /**
     * 题目类别
     */
    @NotBlank(message = "题目类别不能为空！")
    private String name;

    /**
     * 父类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String parentId;

    public CategoryVo() {
    }
}
