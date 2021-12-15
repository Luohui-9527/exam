package exam.demo.moduleauth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import exam.demo.moduleauth.exception.AuthError;
import exam.demo.moduleauth.exception.AuthException;
import exam.demo.moduleauth.mapper.*;
import exam.demo.moduleauth.pojo.dto.UserDto;
import exam.demo.moduleauth.pojo.model.*;
import exam.demo.moduleauth.service.LoginService;
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

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-15
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    @Resource
    UserRoleMapper userRoleMapper;

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleResourceMapper roleResourceMapper;

    @Resource
    ResourceMapper resourceMapper;

    @Resource
    UserOnlineInfoMapper userOnlineInfoMapper;

    @Autowired
    SnowFlake snowFlake;

    @Autowired
    CacheManager cacheManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> createToken(UserDto userDto) {
        UserPermission userPermission;
        User user = CommonUtils.copyProperties(userDto, User.class);
        try {
            userPermission = userMapper.checkUser(user);
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
        if (userOnlineInfoMapper.insert(userOnlineInfo) != 1) {
            throw new AuthException(AuthError.ONLINE_INSERT_FAIL);
        }
        userPermission.setUserOnlineId(userOnlineInfo.getId());
        String token = JwtUtil.createJwt(userPermission);
        Cache tokenCache = cacheManager.getCache(CacheConstants.TOKEN);
        // 根据userId查询是否已经有缓存，如果有token说明已经登录
        Cache.ValueWrapper valueWrapper = tokenCache.get(userPermission.getId());
        if (valueWrapper != null) {
            List<Integer> ids = new ArrayList<>();
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
        User user = userMapper.selectId(id);
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", id);
        List<UserRole> userRoleList = userRoleMapper.selectList(userRoleQueryWrapper);
        List<Role> roleList = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            roleList.add(roleMapper.selectId(userRole.getRoleId()));
        }
        List<RoleResource> roleResourceList = new ArrayList<>();
        for (Role role : roleList) {
            QueryWrapper<RoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
            roleResourceQueryWrapper.eq("role_id", role.getId());
            List<RoleResource> roleResource = roleResourceMapper.selectList(roleResourceQueryWrapper);
            roleResourceList.addAll(roleResource);
        }
        List<exam.demo.moduleauth.pojo.model.Resource> resourceList = resourceMapper.selectBatchIds(roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList()));
        user.setRoles(new HashSet<>(roleList));
        for (Role role : roleList) {
            Set<exam.demo.moduleauth.pojo.model.Resource> set = new HashSet<>();
            for (RoleResource roleResource : roleResourceList) {
                if (role.getId().equals(roleResource.getRoleId())) {
                    for (exam.demo.moduleauth.pojo.model.Resource resource : resourceList) {
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
            return userMapper.getUserInfo(userPermission);
        } catch (Exception e) {
            throw new AuthException(StarterError.SYSTEM_ACCESS_INVALID);
        }
    }

    @Override
    public List<UserMenu> getUserMenu(String token) {
        try {
            UserPermission userPermission = JwtUtil.parseJwt(token);
            QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
            userRoleQueryWrapper.eq("user_id", userPermission.getId());
            List<UserRole> userRole = userRoleMapper.selectList(userRoleQueryWrapper);
            Set<exam.demo.moduleauth.pojo.model.Resource> resourceSet = new LinkedHashSet<>();
            for (UserRole role : userRole) {
                QueryWrapper<RoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
                roleResourceQueryWrapper.eq("role_id", role.getRoleId());
                List<RoleResource> roleResourceList = roleResourceMapper.selectList(roleResourceQueryWrapper);
                resourceSet.addAll(resourceMapper.listByIdList(roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList())));
            }
            return CommonUtils.convertList(resourceSet, UserMenu.class);
        } catch (Exception e) {
            throw new AuthException(StarterError.SYSTEM_ACCESS_INVALID);
        }
    }

    @Override
    public boolean logout(List<Integer> ids) {
        Cache cache = cacheManager.getCache(CacheConstants.TOKEN);
        Cache resourceCache = cacheManager.getCache(CacheConstants.RESOURCE_MAP);
        for (Integer id : ids) {
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
                userOnlineInfoMapper.updateById(userOnlineInfo);
            }
        }
        return true;
    }

}
