package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.model.UserForRole;

import java.util.List;

/**
 * 权限表 - 数据访问接口
 *
 * @author gpmscloud
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 模糊查询及查询全部记录
     *
     * @param role 角色查询条件
     * @return 查询符合条件的角色记录集合
     */
    List<Role> query(Role role);

    /**
     * 查询角色所拥有的资源
     *
     * @return 角色对应的资源集合
     */
    List<UserForRole> queryUserForRoleSP();

    /**
     * 查询角色所拥有的资源
     *
     * @param role 角色
     * @return 角色对应的资源集合
     */
    List<UserForRole> queryUserForRole(Role role);

    /**
     * 查询角色所拥有的资源
     *
     * @param role 角色
     * @return 角色对应的资源集合
     */
    List<TreeList> queryResourceForRole(Role role);

    /**
     * 查询角色
     *
     * @return 所拥有的角色集合
     */
    List<UserOptionsDto> queryRole();

    List<UserOptionsDto> queryRoleByCompany(Integer companyId);

    List<UserOptionsDto> queryRoleByOrg(Integer orgId);
}
