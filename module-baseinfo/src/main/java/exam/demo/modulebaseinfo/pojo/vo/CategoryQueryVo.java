package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 题目类别查询VO
 *
 * @author luohui
 */
@Data
public class CategoryQueryVo extends BaseQueryVo implements Serializable {


    private static final long serialVersionUID = -4473995365972235347L;
    /**
     * 题目类别
     */
    private String name;

    /**
     * 父亲节点id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String parentId;


    public CategoryQueryVo() {

    }

}
