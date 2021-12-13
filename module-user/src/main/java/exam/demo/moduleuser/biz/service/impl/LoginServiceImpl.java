package exam.demo.moduleuser.biz.service.impl;


import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.JwtUtil;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleuser.biz.dao.UserDao;
import exam.demo.moduleuser.biz.dao.UserOnlineInfoDao;
import exam.demo.moduleuser.biz.service.LoginService;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.User;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;
import exam.demo.moduleuser.pojo.vo.UserInfo;
import exam.demo.moduleuser.pojo.vo.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-13
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserDao userDao;

    @Resource
    UserOnlineInfoDao userOnlineInfoDao;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    SnowFlake snowFlake;

    /**
     * 登录
     *
     * @param userDTO
     * @return
     * @throws Exception
     */
    @Override
    public String login(UserDto userDTO) {
        User user = CommonUtils.copyProperties(userDTO, User.class);
        // 查询该用户
        UserPermission userPermission = userDao.checkUser(user);
        if (userPermission == null) {
            throw new UserException(UserError.USER_OR_PASSWORD_ERROR);
        }
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(userDTO, UserOnlineInfo.class);
        userOnlineInfo.setId(snowFlake.nextId());
        if (userPermission.getId() != null) {
            userOnlineInfo.setUserId(userPermission.getId());
        }
        userOnlineInfo.setName(userPermission.getUserName());
        userOnlineInfo.setOnlineTime(new Date());
        userOnlineInfo.setStatus((byte) 1);
        // 增加在线用户
        userOnlineInfoDao.insert(userOnlineInfo);
        userPermission.setUserOnlineId(userOnlineInfo.getId());
        String token = JwtUtil.createJwt(userPermission);
        Cache userPermissionCache = cacheManager.getCache(CacheConstants.USER_PERMISSION);
        // 根据userId查询是否已经有缓存
        Cache.ValueWrapper valueWrapper = userPermissionCache.get(userPermission.getId());
        if (valueWrapper != null) {
            List<Long> ids = new ArrayList<>();
            ids.add(userPermission.getId());
            logout(ids);
        }
        // 存token
        userPermissionCache.put(userPermission.getId(), userPermission);
        return token;
    }

    /**
     * 获取用户信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public UserInfo getUserInfo() {
        UserPermission userPermission = TokenUtils.getUser();
        return userDao.getUserInfo(userPermission);
    }

    /**
     * 更新用户面板
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<UserMenu> getUserMenu() {
        UserPermission userPermission = TokenUtils.getUser();
        return userDao.getUserMenu(userPermission);
    }

    /**
     * 登出
     *
     * @param ids
     * @return
     * @throws Exception
     */
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
                    throw new UserException(StarterError.SYSTEM_TOKEN_PARSE_ERROR);
                }
                cache.evict(id);
                UserOnlineInfo userOnlineInfo = new UserOnlineInfo();
                userOnlineInfo.setId(userPermission.getUserOnlineId());
                userOnlineInfo.setUserId(userPermission.getId());
                userOnlineInfo.setStatus((byte) 0);
                userOnlineInfo.setOfflineTime(new Date());
                userOnlineInfoDao.updateById(userOnlineInfo);
            }
        }
        return true;
    }
}
