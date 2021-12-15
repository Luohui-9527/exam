package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/11
 * @describe 用于资源分配
 */
@Data
public class RoleResourceVo implements Serializable {
    private static final long serialVersionUID = -5392841873474130071L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Integer resourceId;

    private Integer type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleResourceVo that = (RoleResourceVo) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, resourceId);
    }

}
