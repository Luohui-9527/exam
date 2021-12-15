package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class PositionQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -5311406730359216485L;
    /**
     * 职位ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 职位名
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PositionQueryVo() {
    }

    @Override
    public String toString() {
        return "PositionQueryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
