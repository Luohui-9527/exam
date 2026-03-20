package exam.demo.moduleuser.dto;

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
    private String id;
    private List<String> roleIds;
    private String userId;
    private String roleName;

}
