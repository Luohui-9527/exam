package exam.demo.moduleauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.User;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.moduleauth.pojo.model.UserMenu;
import exam.demo.modulecommon.utils.jwt.UserPermission;

import java.util.List;

/**
 * 用户表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface UserMapper extends BaseMapper<User> {

    UserPermission checkUser(User user);

    UserInfo getUserInfo(UserPermission userPermission);

    List<UserMenu> getUserMenu(UserPermission userPermission);

    User findByCode(String code);
}
