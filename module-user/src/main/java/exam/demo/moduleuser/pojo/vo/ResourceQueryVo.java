package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResourceQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -2632967430805439069L;
    /**
     * 节点编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    /**
     * 父亲节点
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String parentId;

    public ResourceQueryVo() {
    }
}
