package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = 8925551092436371680L;
    private String id;
    private String userId;
    private String roleId;

}
