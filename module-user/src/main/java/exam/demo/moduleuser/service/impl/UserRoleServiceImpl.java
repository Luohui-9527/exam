package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.moduleuser.mapper.UserRoleMapper;
import exam.demo.moduleuser.pojo.model.UserRole;
import exam.demo.moduleuser.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户权限关联表 - 服务实现
 *
 * @author gpmscloud
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<UserRole> listByRoleIds(List<Integer> roleIds) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.in(MagicPointConstant.ROLE_ID, roleIds);
        return this.list(wrapper);
    }

    @Override
    public List<UserRole> listByUserIds(List<Integer> userIds) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.in(MagicPointConstant.ROLE_ID, userIds);
        return this.list(wrapper);
    }

    @Override
    public List<UserRole> selectByUserId(Integer userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.ROLE_ID, userId);
        return this.list(wrapper);
    }

    @Override
    public List<UserRole> selectByRoleId(Integer roleId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.ROLE_ID, roleId);
        return this.list(wrapper);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.USER_ID, userId);
        this.remove(wrapper);
    }
}
