package exam.demo.moduleuser.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.*;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.UserForRole;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
public interface RoleService extends IService<Role> {
    /**
     * 功能描述
     *
     * @param roleDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(RoleDto roleDto);

    /**
     * 功能描述
     *
     * @param roleDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(RoleDto roleDto);

    /**
     * 功能描述 删除角色记录
     *
     * @param roleDtoList 角色List集合
     * @return: boolean 删除成功条数
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean delete(List<RoleDto> roleDtoList);

    /**
     * 功能描述 根据请求条件查询符合条件的角色记录集合
     *
     * @param role 请求条件查询信息
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Role>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Role> queryByCondition(Role role);

    /**
     * 功能描述
     *
     * @param roleUserDtoList
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean addUserForRole(List<RoleUserDto> roleUserDtoList);

    /**
     * 功能描述
     *
     * @param resourceDtos
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean addResourceForRole(List<RoleResourceDto> resourceDtos);

    /**
     * 功能描述
     *
     * @param role
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.UserForRole>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<UserForRole> queryUserRole(Role role);

    /**
     * 功能描述 查询角色分配资源的树集合
     *
     * @param roleDTO 角色信息
     * @return: java.util.List<exam.demo.moduleuser.dto.TreeListDto> 以树（treelist）形式返回资源数据
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<TreeListDto> queryResourceForRole(RoleDto roleDTO);

    /**
     * 功能描述 查询角色
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.dto.UserOptionsDto> 角色集合
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<UserOptionsDto> queryRole();

}
