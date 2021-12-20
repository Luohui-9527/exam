package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.pojo.model.UserRole;

import java.util.List;

/**
 * 用户权限关联表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IUserRoleService extends IService<UserRole> {

    /**
     * 功能描述: 根据角色id列表获取对应列表
     *
     * @param roleIds 角色id
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.UserRole>
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> listByRoleIds(List<Long> roleIds);

    /**
     * 功能描述: 根据用户id列表获取对应列表
     *
     * @param userIds
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.UserRole>
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> listByUserIds(List<Long> userIds);

    /**
     * 功能描述: 根据用户id获取权限
     *
     * @param userId
     * @return: exam.demo.moduleuser.pojo.model.UserRole
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> listByUserId(Long userId);

    /**
     * 功能描述: 根据角色id获取用户
     *
     * @param roleId 角色id
     * @return: exam.demo.moduleuser.pojo.model.UserRole
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> selectByRoleId(Long roleId);

    /**
     * 功能描述: 根据用户id删除关联
     *
     * @param userId 用户id
     * @return: void
     * @Author: luohui
     * @date: 2021-12-14
     */
    void deleteByUserId(Long userId);
}
