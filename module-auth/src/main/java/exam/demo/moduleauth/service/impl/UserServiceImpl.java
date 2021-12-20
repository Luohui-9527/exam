package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.UserMapper;
import exam.demo.moduleauth.pojo.model.User;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.moduleauth.service.IUserService;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import org.springframework.stereotype.Service;

/**
 * 用户表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public UserPermission checkUser(User user) {
        return baseMapper.checkUser(user);
    }

    @Override
    public UserInfo getUserInfo(UserPermission userPermission) {
        return baseMapper.getUserInfo(userPermission);
    }
}
