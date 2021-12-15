package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.RoleResource;

import java.util.List;

/**
 * 权限资源关联表 - 服务接口
 *
 * @author gpmscloud
 */
public interface IRoleResourceService extends IService<RoleResource> {

    /**
     * 功能描述: 删除角色资源
     *
     * @param roleId
     * @return: void
     * @Author: luohui
     * @date: 2021-12-14
     */
    void removeByRoleId(Integer roleId);

    /**
     * 功能描述: 删除角色资源
     *
     * @param roleList
     * @return: void
     * @Author: luohui
     * @date: 2021-12-9
     */
    void removeByRoleList(List<Role> roleList);
}
