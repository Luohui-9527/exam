package exam.demo.moduleuser.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/10
 */
@Data
public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = 7771310453968830965L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    @JsonSerialize(using = ToStringSerializer.class)
    private List<Integer> roleIds;
    private Integer userId;
    private String roleName;

}
