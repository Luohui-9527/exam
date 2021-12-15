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
 * 用户表 - 服务接口
 *
 * @author gpmscloud
 */
public interface IUserService extends IService<User> {
    Integer getMostPossibleUserId(String name);

    /**
     * 通过名字查询符合条件的阅卷官信息
     *
     * @param userDto 名字及公司Id
     * @return 阅卷官记录集合
     */
    List<UserDto> queryScoringOfficerList(UserDto userDto);

    CompanyAndUserVo getUserData(List<Integer> longList);

    String getUserName(Integer id);

    boolean save(UserDto userDto);

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
     * @return 符合条件的分页后的用户Map集合
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
    List<TreeList> getQueryListData(Integer judgeId);

    /**
     * 分配角色后需要更新用户公司
     *
     * @param userId
     * @param companyId
     */
    void updateUserAfterAllocRole(Integer userId, Integer companyId);

    /**
     * 查询用户所拥有的角色
     *
     * @param userId 用户ID
     * @return 拥有的角色集合
     */
    List<RoleDto> queryRoleOfUser(Integer userId);

    /**
     * 判断是否存在该用户工号
     *
     * @param code
     * @return
     */
    boolean notExistCode(String code);

    /**
     * 功能描述: 获取用户
     *
     * @param user
     * @return: exam.demo.modulecommon.utils.jwt.UserPermission
     * @Author: luohui
     * @date: 2021-12-14
     */
    UserPermission checkUser(User user);

    /**
     * 功能描述: 获取用户菜单
     *
     * @param userPermission
     * @return: java.util.List<exam.demo.moduleuser.pojo.vo.UserMenu>
     * @Author: luohui
     * @date: 2021-12-14
     */
    List<UserMenu> getUserMenu(UserPermission userPermission);
}
