package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.server.biz.dao.SubjectTypeDao;
import exam.demo.server.biz.service.SubjectTypeService;
import exam.demo.server.dto.SubjectTypeDto;
import exam.demo.server.exception.BaseInfoError;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.model.SubjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 */
@Service
public class SubjectTypeServiceImpl extends ServiceImpl<SubjectTypeDao, SubjectType> implements SubjectTypeService {
    @Autowired
    CacheManager cacheManager;

    @FullCommonField
    @Override
    public boolean save(SubjectTypeDto subjectTypeDto) {
        SubjectType subjectType = CommonUtils.copyProperties(subjectTypeDto, SubjectType.class);
        return save(subjectType);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(List<SubjectType> subjectTypeList) {
        return baseMapper.removeBatch(subjectTypeList);
    }

    @FullCommonField(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(SubjectTypeDto subjectType) {
        SubjectType subjectType2 = CommonUtils.copyProperties(subjectType, SubjectType.class);
        return baseMapper.update(subjectType2);
    }

    @Override
    public List<SubjectType> list(SubjectType subjectType) {
        QueryWrapper<SubjectType> wrapper = new QueryWrapper<>();
        wrapper.eq(SubjectType.ORG_ID, subjectType.getOrgId());
        if (StringUtils.isNotBlank(subjectType.getName())) {
            wrapper.likeRight(SubjectType.NAME, subjectType.getName());
        }
        wrapper.orderByDesc(SubjectType.UPDATE_TIME);
        return list(wrapper);
    }

    @Override
    public List<SubjectType> querySubjectTypeUpdateForm(SubjectType subjectType) {
        return baseMapper.querySubjectTypeUpdateForm(subjectType);
    }

    @Override
    public List<String> getTypeName(List<Long> idList) {
        List<String> res = new ArrayList<>();
        Cache cache = cacheManager.getCache(CacheConstants.SUBJECT_TYPE_VAL);
        for (Long id : idList) {
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper == null) {
                SubjectType subjectType = getById(id);
                if (subjectType == null) {
                    throw new BaseInfoException(BaseInfoError.SUBJECT_TYPE_NOT_EXIST);
                }
                res.add(subjectType.getName());
                cache.put(id, subjectType.getName());
            } else {
                res.add((String) valueWrapper.get());
            }
        }
        return res;
    }

    @Override
    public String getTypeName(Long id) {
        Cache cache = cacheManager.getCache(CacheConstants.SUBJECT_TYPE_VAL);
        Cache.ValueWrapper wrapper = cache.get(id);
        if (wrapper != null) {
            return (String) wrapper.get();
        } else {
            QueryWrapper<SubjectType> subjectTypeQueryWrapper = new QueryWrapper<>();
            subjectTypeQueryWrapper.select(SubjectType.ATTRIBUTE);
            subjectTypeQueryWrapper.eq("id", id);
            SubjectType type = getOne(subjectTypeQueryWrapper);
            if (type == null) {
                throw new BaseInfoException(BaseInfoError.SUBJECT_TYPE_NOT_EXIST);
            }
            cache.put(id, type.getAttribute());
            return type.getAttribute();
        }
    }
}
