package exam.demo.modulecommon.utils;

import com.alibaba.fastjson.JSON;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
public class CommonUtils {
    public static <T> T copyProperties(Object src, Class<T> targetClass){
        try {
            T res = targetClass.newInstance();
            BeanUtils.copyProperties(src,res);
            return res;
        } catch (ReflectiveOperationException e) {
            // do nothing
        }
        throw new StarterException(StarterError.SYSTEM_COPY_PROPERTIES_ERROR);
    }

    public static <T> List<T> convertList(Collection<?> src, Class<T> targetClass){
        if (isEmpty(src) || isEmpty(targetClass)){
            return new ArrayList<>();
        }
        List<T> res = new ArrayList<>();
        for (Object o : src) {
            try {
                T t = targetClass.newInstance();
                BeanUtils.copyProperties(o,t);
                res.add(t);
            } catch (Exception e) {
                throw new StarterException(StarterError.SYSTEM_COPY_PROPERTIES_ERROR);
            }
        }
        return res;
    }

    public static <T> T copyComplicateObject(Object src, Class<T> target){
        return JSON.parseObject(JSON.toJSONString(src),target);
    }

    public static boolean isSuccess(CommonResponse response){
        return "200".equals(response.getCode());
    }

    public static boolean isEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Object[] array){
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object o){ return  o == null;}
    public static boolean notNull(Object ... o){
        if (o == null){return false;}
        for (Object o1 : o) {
            if (o1 == null){
                return false;
            }
        }
        return true;
    }


    public static Long judgeCompanyAndOrg (){
        Long companyId = TokenUtils.getUser().getCompanyId();
        Long orgId = TokenUtils.getUser().getOrgId();
        if(companyId != null){
            return companyId;
        }else{
            return orgId;
        }
    }

}
