package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.*;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.UserForRole;

import java.util.List;

/**
 * 角色服务接口
 * 提供角色的增删改查、用户分配、资源分配等功能
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface IRoleService extends IService<Role> {
    /**
     * 保存角色信息
     *
     * @param roleDto 角色信息
     * @return 是否保存成功
     */
    boolean save(RoleDto roleDto);

    /**
     * 更新角色信息
     *
     * @param roleDto 角色信息
     * @return 是否更新成功
     */
    boolean update(RoleDto roleDto);

    /**
     * 删除角色记录
     *
     * @param roleDtoList 角色List集合
     * @return 删除成功条数
     */
    boolean delete(List<RoleDto> roleDtoList);

    /**
     * 根据请求条件查询符合条件的角色记录集合
     *
     * @param role 请求条件查询信息
     * @return 符合条件的角色集合
     */
    List<Role> queryByCondition(Role role);

    /**
     * 为角色分配用户
     *
     * @param roleUserDtoList 角色用户关联信息列表
     * @return 是否分配成功
     */
    boolean addUserForRole(List<RoleUserDto> roleUserDtoList);

    /**
     * 为角色分配资源
     *
     * @param resourceDtos 角色资源关联信息列表
     * @return 是否分配成功
     */
    void addResourceForRole(List<RoleResourceDto> resourceDtos);

    /**
     * 查询角色下的用户
     *
     * @param role 角色信息
     * @return 角色下的用户列表
     */
    List<UserForRole> queryUserRole(Role role);

    /**
     * 查询角色分配资源的树集合
     *
     * @param roleDTO 角色信息
     * @return 以树（treelist）形式返回资源数据
     */
    List<TreeListDto> queryResourceForRole(RoleDto roleDTO);

    /**
     * 查询角色列表
     *
     * @return 角色集合
     */
    List<UserOptionsDto> queryRole();

}
