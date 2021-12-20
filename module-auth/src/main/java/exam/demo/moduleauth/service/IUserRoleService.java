package exam.demo.moduleauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleauth.pojo.model.UserRole;

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
     * 功能描述: 根据用户id获取权限
     *
     * @param userId
     * @return: exam.demo.moduleuser.pojo.model.UserRole
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserRole> listByUserId(Long userId);
}
