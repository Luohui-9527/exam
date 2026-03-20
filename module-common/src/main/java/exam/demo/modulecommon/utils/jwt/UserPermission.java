package exam.demo.modulecommon.utils.jwt;

import lombok.Data;

/**
 * @author luohui
 */
@Data
public class UserPermission {
    private String id;
    private String userName;
    private String orgId;
    private String companyId;
    private String userOnlineId;

}
