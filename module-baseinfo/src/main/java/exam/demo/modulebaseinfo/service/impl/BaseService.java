package exam.demo.modulebaseinfo.service.impl;

import exam.demo.modulebaseinfo.constant.EnumUserInfoType;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.manage.UserApi;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-05
 */
@Service
public class BaseService {
    @Autowired
    UserApi userApi;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    CommonState state;

    /**
     * 根据Id和类型获取用户数据
     *
     * @param id
     * @param type
     * @return
     */
    public String getUserInfo(Long id, EnumUserInfoType type) {
        Cache cache;
        switch (type) {
            case ORG:
                cache = cacheManager.getCache(CacheConstants.ORG_VAL);
                break;
            case USER:
                cache = cacheManager.getCache(CacheConstants.USER_VAL);
                break;
            case COMPANY:
                cache = cacheManager.getCache(CacheConstants.COMPANY_VAL);
                break;
            default:
                throw new BaseInfoException(StarterError.SYSTEM_CACHE_TYPE_INVALID);
        }
        Cache.ValueWrapper wrapper = cache.get(id);
        if (wrapper == null) {
            String value = queryFromApi(id, type);
            if (value == null) {
                throw new BaseInfoException(StarterError.SYSTEM_RPC_ERROR);
            }
            cache.put(id, value);
            return value;
        }
        return (String) wrapper.get();
    }

    public String queryFromApi(Long id, EnumUserInfoType type) {
        CommonResponse<String> response;
        switch (type) {
            case COMPANY:
                response = userApi.getCompanyById(id);
                if (CommonUtils.isSuccess(response)) {
                    return response.getData();
                } else {
                    throw new BaseInfoException(StarterError.SYSTEM_API_ERROR, response.getMsg());
                }
            case USER:
                response = userApi.getUserNameById(id);
                if (CommonUtils.isSuccess(response)) {
                    return response.getData();
                } else {
                    throw new BaseInfoException(StarterError.SYSTEM_API_ERROR, response.getMsg());
                }
            case ORG:
                return null;
            default:
                throw new BaseInfoException(StarterError.SYSTEM_CACHE_TYPE_INVALID);
        }
    }

}
