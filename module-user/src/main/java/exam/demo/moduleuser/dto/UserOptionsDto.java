package exam.demo.moduleuser.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/11
 */
@Data
public class UserOptionsDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer roleId;
    private String roleName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer positionId;
    private String positionName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer companyId;
}
