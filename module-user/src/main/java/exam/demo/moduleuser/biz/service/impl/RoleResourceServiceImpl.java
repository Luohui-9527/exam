package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleuser.biz.dao.RoleResourceDao;
import exam.demo.moduleuser.biz.service.RoleResourceService;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.RoleResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-14
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceDao, RoleResource> implements RoleResourceService {
    @Override
    public void removeByRoleList(List<Role> roleList) {
        baseMapper.removeBatch(roleList.stream().map(Role::getId).collect(Collectors.toList()));
    }
}
