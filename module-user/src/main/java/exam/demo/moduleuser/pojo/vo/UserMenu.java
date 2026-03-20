package exam.demo.moduleuser.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/9/15
 * @describe 用于获取登陆用户的菜单资源
 */
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
