package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.model.User;
import exam.demo.moduleuser.pojo.vo.UserMenu;

import java.util.List;

/**
 * 用户表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询角色为阅卷官的用户
     *
     * @param user 查询条件
     * @return 符合条件的阅卷官记录集合
     */
    List<User> queryOfficerList(User user);

    /**
     * 查询用户记录输出树
     *
     * @return treelist记录
     */
    List<TreeList> getQueryListData(Long id);

    /**
     * 查询用户
     *
     * @return UserPermission
     */
    UserPermission checkUser(User user);

    /**
     * 查询用户菜单
     *
     * @return UserMenu
     */
    List<UserMenu> getUserMenu(UserPermission userPermission);
}
