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
public class ResourceQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -2632967430805439069L;
    /**
     * 节点编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 父亲节点
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parentId;

    public ResourceQueryVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ResourceQueryVo{" +
                "id='" + id + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
