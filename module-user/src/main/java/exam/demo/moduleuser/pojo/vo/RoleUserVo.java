package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/24
 * @describe 用于角色页面分配用户
 */
@Data
public class RoleUserVo implements Serializable {
    private static final long serialVersionUID = -7978436944833582391L;
    private String id;
    private String userId;
    private String roleId;
    private String companyId;

}
