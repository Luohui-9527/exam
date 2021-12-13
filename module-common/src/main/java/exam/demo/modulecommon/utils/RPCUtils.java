package exam.demo.modulecommon.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-23
 */
public class RPCUtils {
    public static final int BASEINFO = 1;
    public static final int USER = 2;
    public static final int PAPER = 3;
    public static final int EXAM = 4;
    public static final int AUTH = 5;

    public static boolean isSuccess(CommonResponse response){
        return "200".equals(response.getCode());
    }

    public static <T> T parseResponse(CommonResponse response, Class<T> resultType, int RPC_TYPE){
        if (isSuccess(response)){
            // 如果是集合类型
            if (Collection.class.isAssignableFrom(resultType)){
                // JSONObject类型
                Collection<T> result = (Collection<T>) JSON.parseObject(JSON.toJSONString(response.getData()),resultType);
                for (T t : result) {
                    t = JSON.parseObject(JSON.toJSONString(t),resultType);
                }
                return (T) result;
            }
            return JSON.parseObject(JSON.toJSONString(response.getData()),resultType);
        }
        switch (RPC_TYPE) {
            case BASEINFO:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE, "基础信息服务", response.getMsg());
            case USER:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE, "用户服务", response.getMsg());
            case PAPER:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"试卷服务",response.getMsg());
            case EXAM:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"考试服务",response.getMsg());
            case AUTH:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"认证服务",response.getMsg());
            default:
                throw new StarterException(StarterError.SYSTEM_API_ERROR,response.getMsg());
        }
    }

    public static <T> Collection<T> parseCollectionTypeResponse(CommonResponse response, Class<T> resultType, int RPC_TYPE){
        if (isSuccess(response)){
            // JSONObject类型
            Collection<T> result = JSON.parseObject(JSON.toJSONString(response.getData()),Collection.class);
            Collection<T> res = new ArrayList<>();
            for (T t : result) {
                t = JSON.parseObject(JSON.toJSONString(t),resultType);
                res.add(t);
            }
            return res;
        }
        switch (RPC_TYPE) {
            case BASEINFO:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE, "基础信息服务", response.getMsg());
            case USER:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE, "用户服务", response.getMsg());
            case PAPER:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"试卷服务",response.getMsg());
            case EXAM:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"考试服务",response.getMsg());
            case AUTH:
                throw new StarterException(StarterError.SYSTEM_API_ERROR_TYPE,"认证服务",response.getMsg());
            default:
                throw new StarterException(StarterError.SYSTEM_API_ERROR,response.getMsg());
        }
    }
}
