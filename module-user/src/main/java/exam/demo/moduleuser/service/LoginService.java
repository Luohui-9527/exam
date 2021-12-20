package exam.demo.moduleuser.service;


import exam.demo.modulecommon.common.UserDto;
import exam.demo.moduleuser.pojo.vo.UserInfo;
import exam.demo.moduleuser.pojo.vo.UserMenu;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-13
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    String login(UserDto userDTO);

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    UserInfo getUserInfo();

    /**
     * 更新用户面板
     *
     * @return
     * @throws Exception
     */
    List<UserMenu> getUserMenu();

    /**
     * 登出
     *
     * @param ids
     * @return
     * @throws Exception
     */
    boolean logout(List<Long> ids);
}
