package exam.demo.moduleauth.service;



import exam.demo.moduleauth.pojo.dto.UserDto;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.moduleauth.pojo.model.UserMenu;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-15
 */
public interface LoginService {
    Map<String,Object> createToken(UserDto userDTO);
    UserInfo getUserInfo(String token);
    List<UserMenu> getUserMenu(String token);
    boolean logout(List<Integer> ids);
}
