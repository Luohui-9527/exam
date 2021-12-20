package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.UserRoleMapper;
import exam.demo.moduleauth.pojo.model.UserRole;
import exam.demo.moduleauth.service.IUserRoleService;
import exam.demo.modulecommon.constant.MagicPointConstant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户权限关联表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<UserRole> listByUserId(Long userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.USER_ID, userId);
        return this.list(wrapper);
    }

}
