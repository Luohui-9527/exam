package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.moduleuser.mapper.RoleResourceMapper;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.RoleResource;
import exam.demo.moduleuser.service.IRoleResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限资源关联表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    public void removeByRoleId(Long roleId) {
        QueryWrapper<RoleResource> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.ROLE_ID, roleId);
        this.remove(wrapper);
    }

    @Override
    public void removeByRoleList(List<Role> roleList) {
        List<Long> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());
        QueryWrapper<RoleResource> wrapper = new QueryWrapper<>();
        wrapper.in(MagicPointConstant.ROLE_ID, roleIdList);
        this.remove(wrapper);
    }
}