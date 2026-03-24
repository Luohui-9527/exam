package exam.demo.modulecommon.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.utils.jwt.UserPermission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
public class CommonUtils {
    public static <T> T copyProperties(Object source, Class<T> targetClass, String... ignoreParam) {
        if (source == null) {
            return null;
        }
        CopyOptions copyOptions = CopyOptions.create()
                .setIgnoreError(true)
                .setIgnoreNullValue(true);
        if (ObjectUtil.isNotEmpty(ignoreParam) && ignoreParam.length > 0) {
            copyOptions.setIgnoreProperties(ignoreParam);
        }
        return BeanUtil.toBean(source, targetClass, copyOptions);
    }

    public static <T> List<T> convertList(Collection<?> sourceList, Class<T> targetClass, String... ignoreParam) {
        if (CollUtil.isEmpty(sourceList) || targetClass == null) {
            return new ArrayList<>();
        }
        List<T> targetList = new ArrayList<>();
        for (Object item : sourceList) {
            targetList.add(copyProperties(item, targetClass, ignoreParam));
        }
        return targetList;
    }

    public static <T> T copyComplicateObject(Object src, Class<T> target) {
        return JSON.parseObject(JSON.toJSONString(src), target);
    }

    public static boolean isSuccess(CommonResponse response) {
        return "200".equals(response.getCode());
    }

    public static boolean notNull(Object... o) {
        if (o == null) {
            return false;
        }
        for (Object o1 : o) {
            if (o1 == null) {
                return false;
            }
        }
        return true;
    }


    public static String judgeCompanyAndOrg() {
        try {
            UserPermission user = TokenUtils.getUser();
            if (user != null) {
                String companyId = user.getCompanyId();
                String orgId = user.getOrgId();
                if (companyId != null) {
                    return companyId;
                } else {
                    return orgId;
                }
            }
        } catch (Exception e) {
            // 忽略异常，返回null
        }
        return null;
    }

}
