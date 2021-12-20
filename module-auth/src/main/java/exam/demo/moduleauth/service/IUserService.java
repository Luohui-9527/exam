package exam.demo.moduleauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleauth.pojo.model.User;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.modulecommon.utils.jwt.UserPermission;

/**
 * 用户表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IUserService extends IService<User> {
    UserPermission checkUser(User user);

    UserInfo getUserInfo(UserPermission userPermission);
}
