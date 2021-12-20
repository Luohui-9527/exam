package exam.demo.moduleauth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import exam.demo.moduleauth.exception.AuthError;
import exam.demo.moduleauth.exception.AuthException;
import exam.demo.moduleauth.pojo.dto.UserDto;
import exam.demo.moduleauth.pojo.model.*;
import exam.demo.moduleauth.service.*;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.modulecommon.utils.jwt.JwtUtil;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-15
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private IRoleResourceService roleResourceService;

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserOnlineInfoService userOnlineInfoService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private CacheManager cacheManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> createToken(UserDto userDto) {
        UserPermission userPermission;
        User user = CommonUtils.copyProperties(userDto, User.class);
        try {
            userPermission = userService.checkUser(user);
            if (userPermission == null) {
                throw new AuthException(AuthError.USER_NOT_EXIST);
            }
        } catch (Exception e) {
            throw new AuthException(AuthError.USER_NOT_EXIST);
        }
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(userDto, UserOnlineInfo.class);
        userOnlineInfo.setId(snowFlake.nextId());
        userOnlineInfo.setUserId(userPermission.getId());
        userOnlineInfo.setName(userPermission.getUserName());
        userOnlineInfo.setOnlineTime(new Date());
        userOnlineInfo.setStatus(1);
        if (!userOnlineInfoService.save(userOnlineInfo)) {
            throw new AuthException(AuthError.ONLINE_INSERT_FAIL);
        }
        userPermission.setUserOnlineId(userOnlineInfo.getId());
        String token = JwtUtil.createJwt(userPermission);
        Cache tokenCache = cacheManager.getCache(CacheConstants.TOKEN);
        // 根据userId查询是否已经有缓存，如果有token说明已经登录
        Cache.ValueWrapper valueWrapper = tokenCache.get(userPermission.getId());
        if (valueWrapper != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(userPermission.getId());
            logout(ids);
        }
        // 更新token
        tokenCache.put(userPermission.getId(), token);
        user = findById(userPermission.getId());
        Map<String, String> urlMap = new HashMap<>();
        for (Role role : user.getRoles()) {
            for (exam.demo.moduleauth.pojo.model.Resource resource : role.getResources()) {
                urlMap.put(String.valueOf(resource.getId()), resource.getUrl());
            }
        }
        // 更新资源
        Cache resourceCache = cacheManager.getCache(CacheConstants.RESOURCE_MAP);
        resourceCache.put(userPermission.getId(), urlMap);
        Map<String, Object> data = new HashMap<>(1);
        data.put("token", token);
        return data;
    }


    private User findById(long id) {
        User user = userService.getById(id);
        List<UserRole> userRoleList = userRoleService.listByUserId(id);
        List<Role> roleList = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roleList.add(roleService.getById(userRole.getRoleId()));
        }
        List<RoleResource> roleResourceList = new ArrayList<>();
        for (Role role : roleList) {
            QueryWrapper<RoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
            roleResourceQueryWrapper.eq("role_id", role.getId());
            List<RoleResource> roleResource = roleResourceService.list(roleResourceQueryWrapper);
            roleResourceList.addAll(roleResource);
        }
        List<Resource> resourceList = resourceService.listByIds(roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList()));
        user.setRoles(new HashSet<>(roleList));
        for (Role role : roleList) {
            Set<Resource> set = new HashSet<>();
            for (RoleResource roleResource : roleResourceList) {
                if (role.getId().equals(roleResource.getRoleId())) {
                    for (Resource resource : resourceList) {
                        if (resource.getId().equals(roleResource.getResourceId())) {
                            set.add(resource);
                        }
                    }
                }
            }
            role.setResources(set);
        }
        return user;
    }

    @Override
    public UserInfo getUserInfo(String token) {
        try {
            UserPermission userPermission = JwtUtil.parseJwt(token);
            return userService.getUserInfo(userPermission);
        } catch (Exception e) {
            throw new AuthException(StarterError.SYSTEM_ACCESS_INVALID);
        }
    }

    @Override
    public List<UserMenu> getUserMenu(String token) {
        try {
            UserPermission userPermission = JwtUtil.parseJwt(token);
            List<UserRole> userRoleList = userRoleService.listByUserId(userPermission.getId());
            Set<Resource> resourceSet = new LinkedHashSet<>();
            for (UserRole userRole : userRoleList) {
                QueryWrapper<RoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
                roleResourceQueryWrapper.eq("role_id", userRole.getRoleId());
                List<RoleResource> roleResourceList = roleResourceService.list(roleResourceQueryWrapper);
                resourceSet.addAll(resourceService.listByIds(roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList())));
            }
            return CommonUtils.convertList(resourceSet, UserMenu.class);
        } catch (Exception e) {
            throw new AuthException(StarterError.SYSTEM_ACCESS_INVALID);
        }
    }

    @Override
    public boolean logout(List<Long> ids) {
        Cache cache = cacheManager.getCache(CacheConstants.TOKEN);
        Cache resourceCache = cacheManager.getCache(CacheConstants.RESOURCE_MAP);
        for (Long id : ids) {
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null) {
                resourceCache.evict(id);
                UserPermission userPermission;
                try {
                    userPermission = JwtUtil.parseJwt(String.valueOf(valueWrapper.get()));
                } catch (Exception e) {
                    throw new AuthException(StarterError.SYSTEM_TOKEN_PARSE_ERROR);
                }
                cache.evict(id);
                UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
                userOnlineInfo.setId(userPermission.getUserOnlineId());
                userOnlineInfo.setUserId(userPermission.getId());
                userOnlineInfo.setStatus(0);
                userOnlineInfo.setOfflineTime(new Date());
                userOnlineInfoService.updateById(userOnlineInfo);
            }
        }
        return true;
    }

}
