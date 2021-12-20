package exam.demo.moduleuser.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于角色页面分配用户
 */
@Data
public class RoleUserDto implements Serializable {
    private static final long serialVersionUID = -4555168027630926134L;
    private Long id;
    private Long userId;
    private Long roleId;
    private Long companyId;
}
