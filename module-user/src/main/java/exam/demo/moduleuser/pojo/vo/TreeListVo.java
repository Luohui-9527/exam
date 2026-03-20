package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 */
@Data
public class TreeListVo implements Serializable {
    private static final long serialVersionUID = -1830434213241855994L;
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private String parentId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long rootId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;

}
