package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleuser.biz.dao.UserRoleDao;
import exam.demo.moduleuser.biz.service.UserRoleService;
import exam.demo.moduleuser.pojo.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {
    @Override
    public List<UserRole> listByRoleId(List<Long> roleId) {
        return baseMapper.listByRoleIdList(roleId);
    }

    @Override
    public List<UserRole> listByUserId(List<Long> userId) {
        return baseMapper.listByUserIdList(userId);
    }

    @Override
    public UserRole selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public UserRole selectByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }
}
