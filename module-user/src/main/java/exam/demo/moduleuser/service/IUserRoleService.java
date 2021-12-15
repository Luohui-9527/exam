package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.pojo.model.UserRole;

import java.util.List;

/**
 * 用户权限关联表 - 服务接口
 *
 * @author gpmscloud
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
    List<UserRole> listByRoleIds(List<Integer> roleIds);

    /**
     * 功能描述: 根据用户id列表获取对应列表
     *
     * @param userIds
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.UserRole>
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> listByUserIds(List<Integer> userIds);

    /**
     * 功能描述: 根据用户id获取权限
     *
     * @param userId
     * @return: exam.demo.moduleuser.pojo.model.UserRole
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> selectByUserId(Integer userId);

    /**
     * 功能描述: 根据角色id获取用户
     *
     * @param roleId 角色id
     * @return: exam.demo.moduleuser.pojo.model.UserRole
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> selectByRoleId(Integer roleId);

    /**
     * 功能描述: 根据用户id删除关联
     *
     * @param userId 用户id
     * @return: void
     * @Author: luohui
     * @date: 2021-12-14
     */
    void deleteByUserId(Integer userId);
}
