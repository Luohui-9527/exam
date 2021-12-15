package exam.demo.moduleauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.User;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.moduleauth.pojo.model.UserMenu;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserPermission checkUser(User user);

    UserInfo getUserInfo(UserPermission userPermission);

    List<UserMenu> getUserMenu(UserPermission userPermission);

    User findByCode(String code);

    User selectId(long id);

    boolean exist(@Param("id") long id);
}
