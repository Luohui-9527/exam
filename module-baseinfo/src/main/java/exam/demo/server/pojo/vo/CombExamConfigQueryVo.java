package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * 组卷配置查询VO
 *
 * @author
 */
public class CombExamConfigQueryVo extends BaseQueryVo implements Serializable {

    private static final long serialVersionUID = 697915909468078349L;

    /**
     * 组卷配置id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 组卷配置名
     */
    private String name;

    public CombExamConfigQueryVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CombExamConfigQueryVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentPage=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
