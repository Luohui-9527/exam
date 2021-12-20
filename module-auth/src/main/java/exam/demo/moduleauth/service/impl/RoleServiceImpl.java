package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.RoleMapper;
import exam.demo.moduleauth.pojo.model.Role;
import exam.demo.moduleauth.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * 权限表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}