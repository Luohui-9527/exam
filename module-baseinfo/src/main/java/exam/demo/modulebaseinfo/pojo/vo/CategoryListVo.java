package exam.demo.modulebaseinfo.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目类别返回VO
 *
 * @author
 */
@Data
public class CategoryListVo extends BaseListVo implements Serializable {

    private static final long serialVersionUID = 1955783486638017029L;

    /**
     * 父类别id
     */
    private String parentId;

    /**
     * 父类别名
     */
    private String parentName;

    public CategoryListVo() {
    }

}
