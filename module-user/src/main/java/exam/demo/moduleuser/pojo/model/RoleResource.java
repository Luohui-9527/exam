package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author luohui
 */
@Data
@Accessors(chain = true)
public class RoleResource extends Model<RoleResource> implements Serializable {
    private static final long serialVersionUID = -5559929207907784262L;
    private Long id;
    private Long roleId;
    private Long resourceId;
    private Byte type;

    public RoleResource() {
    }

    public RoleResource(Long id, Long roleId, Long resourceId, Byte type) {
        this.id = id;
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
