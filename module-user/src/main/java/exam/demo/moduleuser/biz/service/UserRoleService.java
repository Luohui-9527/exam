package exam.demo.moduleuser.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.pojo.model.UserRole;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
public interface UserRoleService extends IService<UserRole> {
    List<UserRole> listByRoleId(List<Long> roleId);

    List<UserRole> listByUserId(List<Long> userId);

    UserRole selectByUserId(Long userId);

    UserRole selectByRoleId(Long roleId);
}
