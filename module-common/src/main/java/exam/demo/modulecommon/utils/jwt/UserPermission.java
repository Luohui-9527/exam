package exam.demo.modulecommon.utils.jwt;

import lombok.Data;

/**
 * @author luohui
 */
@Data
public class UserPermission {
    private Integer id;
    private String userName;
    private Integer orgId;
    private Integer companyId;
    private Integer userOnlineId;

}
