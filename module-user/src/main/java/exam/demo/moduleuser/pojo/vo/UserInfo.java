package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5441728009439347597L;
    private String name;
    private String profilePicture;
    private Integer roleId;
    private Integer companyId;

}
