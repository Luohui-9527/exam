package exam.demo.modulecommon.utils.jwt;

import lombok.Data;

/**
 * @author luohui
 */
@Data
public class UserPermission {
    private Long id;
    private String userName;
    private Long orgId;
    private Long companyId;
    private Long userOnlineId;

}
