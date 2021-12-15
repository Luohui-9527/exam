package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * 树列表VO
 */
@Data
public class TreeListVo implements Serializable {


    private static final long serialVersionUID = -1037506771169163112L;
    /**
     * id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父亲id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer parentId;

    /**
     * 版本
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long version;
}
