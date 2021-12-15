package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class DepartmentQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -7089081877518720169L;
    /**
     * 部门id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 部门名
     */
    @NotNull(message = "部门名不能为空")
    private String name;
    /**
     * 部门等级
     */
    @NotNull(message = "部门等级不能为空")
    private String level;

    public DepartmentQueryVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DepartmentQueryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
