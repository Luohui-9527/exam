package exam.demo.moduleuser.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.RoleResource;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
public interface RoleResourceService extends IService<RoleResource> {
    /**
     * 功能描述
     *
     * @param roleList
     * @return: void
     * @Author: luohui
     * @date: 2021-12-9
     */
    void removeByRoleList(List<Role> roleList);
}
