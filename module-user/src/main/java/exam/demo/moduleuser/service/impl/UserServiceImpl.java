package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.common.CompanyAndUserVo;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.*;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleuser.dto.CompanyDto;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.UserRoleDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.mapper.UserMapper;
import exam.demo.moduleuser.pojo.model.*;
import exam.demo.moduleuser.pojo.vo.UserMenu;
import exam.demo.moduleuser.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户表 - 服务实现
 *
 * @author gpmscloud
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    SnowFlake snowFlake;


    @Transactional(rollbackFor = Exception.class)
    @FullCommonFieldU
    @Override
    public boolean save(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto, User.class);
        UserRole userRole = CommonUtils.copyProperties(user, UserRole.class);
        // 设置用户名为盐
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        // 32次散列
        String pwd = new SimpleHash("MD5", user.getPassword(), salt, 32).toString();
        user.setPassword(pwd);
        // 将公司设置为角色所在公司
        Role role = roleService.getById(userDto.getRoleId());
        user.setCompanyId(role.getCompanyId());
        try {
            save(user);
            userRole.setId(snowFlake.nextId());
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);
        } catch (Exception e) {
            throw new UserException(UserError.SAVE_FAIL);
        }
        return true;
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto, User.class);
        ByteSource salt = ByteSource.Util.bytes(user.getName());
        String pwd = new SimpleHash("MD5", user.getPassword(), salt, 32).toString();
        user.setPassword(pwd);
        // 如果职位不属于该公司则报错
        if (userDto.getPositionId() != null) {
            Position position = positionService.getById(userDto.getPositionId());
            if (!position.getCompanyId().equals(userDto.getCompanyId())) {
                throw new UserException(UserError.POSITION_INVALID);
            }
        }
        if (updateById(user)) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    /**
     * 删除用户记录
     *
     * @param userDTOList 用户List集合
     * @return 删除成功条数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<UserDto> userDTOList) {
        List<User> userList = CommonUtils.convertList(userDTOList, User.class);
        for (User user : userList) {
            user.setJudgeId(CommonUtils.judgeCompanyAndOrg());
            if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())) {
                user.setJudgeId(null);
            }
        }
        // 先删除角色
        baseMapper.deleteBatchIds(userList);
        // 再删除用户
        int count = 0;
        for (User user : userList) {
            if (baseMapper.deleteById(user.getId()) == 1) {
                count++;
            } else {
                throw new UserException(UserError.DEL_USER_FAIL, user.getName());
            }
        }
        if (count == userList.size()) {
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    /**
     * 根据请求条件查询符合条件的用户记录集合
     *
     * @param userDto 请求条件查询信息
     * @return 符合条件的分页后的用户Map集合
     */
    @Override
    public List<User> queryByCondition(UserDto userDto) {
        List<User> userList = new ArrayList<>();
        if (AdminUtil.isSuperAdmin()) {
            userList = list();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        // 对于公司管理员来说
        if (TokenUtils.getUser().getCompanyId() != null) {
            userQueryWrapper.eq("company_id", TokenUtils.getUser().getCompanyId());
            if (StringUtils.isNotBlank(userDto.getName())) {
                userQueryWrapper.likeRight("name", userDto.getName());
            }
            if (StringUtils.isNotBlank(userDto.getCode())) {
                userQueryWrapper.eq("code", userDto.getName());
            }
            if (StringUtils.isNotBlank(userDto.getTel())) {
                userQueryWrapper.eq("tel", userDto.getTel());
            }
            userList = list(userQueryWrapper);
            if (CommonUtils.isEmpty(userList)) {
                return null;
            }
        }
        // 对于机构管理员来说
        else if (!AdminUtil.isSuperAdmin()) {
            // user 没有org id
            // 先查询机构的角色
            List<Role> roleList = roleService.queryByCondition(new Role());
//            // 去关联表查询已经是本机构的人员
            List<UserRole> userRoleList = userRoleService.listByRoleIds(roleList.stream().map(Role::getId).collect(Collectors.toList()));
            userList = listByIds(userRoleList.stream().map(UserRole::getUserId).collect(Collectors.toList()));

        }
        // 做右匹配
        if (StringUtils.isNotBlank(userDto.getName())) {
            userList = userList.stream().filter(u -> SqlUtil.like(userDto.getName(), u.getName())).collect(Collectors.toList());
        }
        // 对于工号的处理
        if (StringUtils.isNotBlank(userDto.getCode())) {
            userList = userList.stream().filter(u -> userDto.getCode().equals(u.getCode())).collect(Collectors.toList());
        }
        // 对手机的处理
        if (StringUtils.isNotBlank(userDto.getTel())) {
            userList = userList.stream().filter(u -> userDto.getTel().equals(u.getTel())).collect(Collectors.toList());
        }
        if (CommonUtils.isEmpty(userList)) {
            return null;
        }
        // 对角色的处理
        if (userDto.getRoleId() != null) {
            List<Integer> idList = new ArrayList<>();
            idList.add(userDto.getRoleId());
            List<UserRole> userRoleList = userRoleService.listByRoleIds(idList);
            List<UserRole> userRoles = userRoleList.stream().filter(u -> userDto.getRoleId().equals(u.getRoleId())).collect(Collectors.toList());
            List<Integer> userIdList = userRoles.stream().map(UserRole::getUserId).collect(Collectors.toList());
            userList = userList.stream().filter(u -> userIdList.contains(u.getId())).peek(u -> u.setRoleId(userDto.getRoleId())).collect(Collectors.toList());
        }
        if (CommonUtils.isEmpty(userList)) {
            return null;
        }
        // 为这个User设置roleId
        List<UserRole> userRoleList = userRoleService.listByUserIds(userList.stream().map(User::getId).collect(Collectors.toList()));
        for (User user : userList) {
            for (UserRole userRole : userRoleList) {
                if (user.getId().equals(userRole.getUserId())) {
                    user.setRoleId(userRole.getRoleId());
                    break;
                }
            }
        }
        // 为User设置company
        for (User user : userList) {
            if (user.getCompanyId() == null) {
                // 根据role查询
                Role role = roleService.getById(user.getRoleId());
                user.setCompanyId(role.getCompanyId());
                user.setRoleId(role.getId());
            }
        }
        Map<Integer, String> cache = new HashMap<>(userList.size() << 1);
        for (User user : userList) {
            String val;
            if (user.getDepartmentId() != null) {
                if (!StringUtils.isNotBlank(val = cache.get(user.getDepartmentId()))) {
                    val = departmentService.getById(user.getDepartmentId()).getName();
                    cache.put(user.getDepartmentId(), val);
                }
                user.setDepartmentName(val);
            }
            if (user.getCompanyId() != null) {
                if (!StringUtils.isNotBlank(val = cache.get(user.getCompanyId()))) {
                    val = companyService.getById(user.getCompanyId()).getName();
                    cache.put(user.getCompanyId(), val);
                }
                user.setCompanyName(val);
            }
            if (user.getPositionId() != null) {
                Position p = null;
                if (StringUtils.isNotBlank(val = cache.get(user.getPositionId()))) {
                    user.setPositionName(val);
                } else {
                    p = positionService.getById(user.getPositionId());
                    cache.put(user.getPositionId(), p.getName());
                    user.setPositionName(p.getName());
                }
                if (user.getCompanyId() == null) {
                    if (p != null) {
                        Company company = companyService.getById(p.getCompanyId());
                        if (company != null) {
                            user.setCompanyName(company.getName());
                        }
                    } else {
                        p = positionService.getById(user.getPositionId());
                        Company company = companyService.getById(p.getCompanyId());
                        if (company != null) {
                            user.setCompanyName(company.getName());
                        }
                    }
                }
            }
            if (user.getRoleId() != null) {
                if (!StringUtils.isNotBlank(val = cache.get(user.getRoleId()))) {
                    val = roleService.getById(user.getRoleId()).getName();
                    cache.put(user.getRoleId(), val);
                }
                user.setRoleName(val);
            }
        }
        return userList;
    }

    /**
     * 为用户分配角色
     *
     * @param userRoleDTO 用户角色信息
     * @return 是否分配成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRoleForUser(UserRoleDto userRoleDTO) {
        try {
            // 先删除user_role
            userRoleService.deleteByUserId(userRoleDTO.getUserId());
            List<UserRole> userRoleList = new ArrayList<>();
            // 重新绑定角色关系
            for (Integer roleId : userRoleDTO.getRoleIds()) {
                UserRole userRole = CommonUtils.copyProperties(userRoleDTO, UserRole.class);
                userRole.setId(snowFlake.nextId());
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
            if (!CommonUtils.isEmpty(userRoleList)) {
                userRoleService.saveBatch(userRoleList);
            }
            // 同时要更新用户的companyId
            Role role = roleService.getById(userRoleDTO.getRoleIds().get(0));
            this.updateUserAfterAllocRole(userRoleDTO.getUserId(), role.getCompanyId());
        } catch (Exception e) {
            throw new UserException(UserError.ALLOC_FAIL);
        }
        return true;
    }

    /**
     * 获取公司和用户名称 第一个为公司，第二个为用户
     *
     * @param longList
     * @return
     */
    @Override
    public CompanyAndUserVo getUserData(List<Integer> longList) {
        CompanyAndUserVo res = new CompanyAndUserVo();
        Integer company = longList.get(0);
        Integer userId = longList.get(1);
        Cache companyCache = cacheManager.getCache(CacheConstants.COMPANY_VAL);
        Cache userCache = cacheManager.getCache(CacheConstants.USER_VAL);
        Cache.ValueWrapper companyWrapper = companyCache.get(company);
        if (companyWrapper == null) {
            Company companyFromDatabase = companyService.getById(company);
            if (companyFromDatabase == null) {
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            res.setCompany(companyFromDatabase.getName());
            companyCache.put(company, companyFromDatabase.getName());
        } else {
            res.setCompany((String) companyWrapper.get());
        }
        Cache.ValueWrapper userWrapper = userCache.get(userId);
        if (userWrapper == null) {
            User userFromDatabase = this.getById(userId);
            if (userFromDatabase == null) {
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            res.setUser(userFromDatabase.getName());
            userCache.put(userId, userFromDatabase.getName());
        } else {
            res.setUser((String) userWrapper.get());
        }
        return res;
    }

    @Override
    public String getUserName(Integer id) {
        Cache userCache = cacheManager.getCache(CacheConstants.USER_VAL);
        Cache.ValueWrapper userWrapper = userCache.get(id);
        if (userWrapper == null) {
            User user = this.getById(id);
            if (user == null) {
                throw new UserException(UserError.DATA_NOT_EXIST);
            }
            userCache.put(id, user.getName());
            return user.getName();
        } else {
            return (String) userWrapper.get();
        }
    }

    /**
     * 通过名字查询符合条件的阅卷官信息
     *
     * @param userDto 名字及公司Id
     * @return 阅卷官记录集合
     */
    @Override
    public List<UserDto> queryScoringOfficerList(UserDto userDto) {
        User user = CommonUtils.copyProperties(userDto, User.class);
        List<User> userList = baseMapper.queryOfficerList(user);
        return CommonUtils.convertList(userList, UserDto.class);
    }

    @Override
    // TODO 此接口需优化
    public Integer getMostPossibleUserId(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.NAME, name);
        return this.list(wrapper).get(0).getId();
    }

    /**
     * 获取用户树集合
     *
     * @param judgeId 组织机构Id或公司Id
     * @return 以树（treelist）形式返回数据
     */
    @Override
    public List<TreeList> getQueryListData(Integer judgeId) {
        if (AdminUtil.isSuperAdmin()) {
            List<TreeList> res = new ArrayList<>();
            List<Organization> organizationList = organizationService.list();
            for (Organization organization : organizationList) {
                res.addAll(baseMapper.getQueryListData(organization.getId()));
            }
            return res;
        }
        if (judgeId.equals(TokenUtils.getUser().getOrgId())) {
            judgeId = null;
        }
        List<TreeList> data = baseMapper.getQueryListData(judgeId);
        // 去掉不是该用户的机构的数据 id为companyId
        List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(TokenUtils.getUser().getOrgId()).build());
        List<TreeList> res = new ArrayList<>();
        for (Company company : companyList) {
            for (TreeList treeList : data) {
                if (treeList.getId().equals(company.getId())) {
                    res.add(treeList);
                }
            }
        }
        return res;
    }

    /**
     * 分配角色后需要更新用户公司
     *
     * @param userId
     * @param companyId
     */
    @Override
    public void updateUserAfterAllocRole(Integer userId, Integer companyId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq(MagicPointConstant.ID, userId);
        wrapper.set(MagicPointConstant.COMPANY_ID, companyId);
        if (!update(wrapper)) {
            throw new UserException(UserError.UPDATE_COMPANY_FAIL);
        }
    }

    /**
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<RoleDto> queryRoleOfUser(Integer userId) {
        List<UserRole> userRoleList = userRoleService.selectByUserId(userId);
        List<Integer> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Role> roleList = roleService.listByIds(roleIds);
        List<RoleDto> roleDtoList = CommonUtils.convertList(roleList, RoleDto.class);
        return roleDtoList;
    }

    /**
     * 判断是否存在该用户工号
     *
     * @param code
     * @return
     */
    @Override
    public boolean notExistCode(String code) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.CODE, code);
        return count(wrapper) == 0;
    }

    @Override
    public UserPermission checkUser(User user) {
        return baseMapper.checkUser(user);
    }

    @Override
    public List<UserMenu> getUserMenu(UserPermission userPermission) {
        return baseMapper.getUserMenu(userPermission);
    }
}
