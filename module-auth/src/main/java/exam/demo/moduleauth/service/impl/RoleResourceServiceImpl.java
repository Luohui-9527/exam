package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.RoleResourceMapper;
import exam.demo.moduleauth.pojo.model.RoleResource;
import exam.demo.moduleauth.service.IRoleResourceService;
import org.springframework.stereotype.Service;

/**
 * 权限资源关联表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {
}