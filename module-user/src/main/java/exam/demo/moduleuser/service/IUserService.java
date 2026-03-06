package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulecommon.common.CompanyAndUserVo;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.UserRoleDto;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.model.User;
import exam.demo.moduleuser.pojo.vo.UserMenu;

import java.util.List;

/**
 * 用户服务接口
 * 提供用户的增删改查、角色分配、权限管理等功能
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface IUserService extends IService<User> {
    /**
     * 根据用户名获取最可能的用户ID
     *
     * @param name 用户名
     * @return 用户ID
     */
    Long getMostPossibleUserId(String name);

    /**
     * 通过名字查询符合条件的阅卷官信息
     *
     * @param userDto 名字及公司Id
     * @return 阅卷官记录集合
     */
    List<UserDto> queryScoringOfficerList(UserDto userDto);

    /**
     * 获取用户数据
     *
     * @param longList 用户ID列表
     * @return 公司和用户信息
     */
    CompanyAndUserVo getUserData(List<Long> longList);

    /**
     * 根据用户ID获取用户名
     *
     * @param id 用户ID
     * @return 用户名
     */
    String getUserName(Long id);

    /**
     * 保存用户信息
     *
     * @param userDto 用户信息
     * @return 是否保存成功
     */
    boolean save(UserDto userDto);

    /**
     * 更新用户信息
     *
     * @param userDto 用户信息
     * @return 是否更新成功
     */
    boolean update(UserDto userDto);

    /**
     * 删除用户记录
     *
     * @param userDTOList 用户List集合
     * @return 删除成功条数
     */
    boolean delete(List<UserDto> userDTOList);

    /**
     * 根据请求条件查询符合条件的用户记录集合
     *
     * @param userDto 请求条件查询信息
     * @return 符合条件的用户集合
     */
    List<User> queryByCondition(UserDto userDto);

    /**
     * 为用户分配角色
     *
     * @param userRoleDTO 用户角色信息
     * @return 是否分配成功
     */
    boolean addRoleForUser(UserRoleDto userRoleDTO);

    /**
     * 获取用户树集合
     *
     * @param judgeId 组织机构Id或公司Id
     * @return 以树（treelist）形式返回数据
     */
    List<TreeList> getQueryListData(Long judgeId);

    /**
     * 分配角色后需要更新用户公司
     *
     * @param userId 用户ID
     * @param companyId 公司ID
     */
    void updateUserAfterAllocRole(Long userId, Long companyId);

    /**
     * 查询用户所拥有的角色
     *
     * @param userId 用户ID
     * @return 拥有的角色集合
     */
    List<RoleDto> queryRoleOfUser(Long userId);

    /**
     * 判断是否存在该用户工号
     *
     * @param code 用户工号
     * @return 是否存在
     */
    boolean notExistCode(String code);

    /**
     * 获取用户权限信息
     *
     * @param user 用户信息
     * @return 用户权限信息
     */
    UserPermission checkUser(User user);

    /**
     * 获取用户菜单
     *
     * @param userPermission 用户权限信息
     * @return 用户菜单列表
     */
    List<UserMenu> getUserMenu(UserPermission userPermission);
}
