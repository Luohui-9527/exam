package exam.demo.moduleauth.pojo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserMenu implements Serializable {
    private static final long serialVersionUID = -3364986961375400850L;
    private String id;
    private String name;
    private String code;
    private String parentId;
    private String url;
    private String openImg;
    private String closeImg;
    private byte resourceType;

}
