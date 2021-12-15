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
    private Integer id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parentId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer rootId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;

}
