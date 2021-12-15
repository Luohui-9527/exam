package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.server.biz.dao.DictionaryDao;
import exam.demo.server.biz.service.DictionaryService;
import exam.demo.server.dto.DictionaryDto;
import exam.demo.server.exception.BaseInfoError;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.model.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-02
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryDao, Dictionary> implements DictionaryService {
    @Autowired
    CacheManager cacheManager;

    /**
     * 获取字典值
     *
     * @param idList
     * @return
     */
    @Override
    public List<String> getDictionary(List<Integer> idList) {
        Cache cache = cacheManager.getCache(CacheConstants.DICTIONARY);
        List<String> stringList = new ArrayList<>();
        for (Integer id : idList) {
            Dictionary dictionary;
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper == null) {
                dictionary = getById(id);
                cache.put(id, dictionary);
            } else {
                dictionary = (Dictionary) valueWrapper.get();
            }
            if (dictionary == null) {
                throw new BaseInfoException(BaseInfoError.DICTIONARY_NOT_EXIST);
            }
            stringList.add(dictionary.getValue());
        }
        return stringList;
    }

    /**
     * 获取值
     *
     * @param id
     * @return
     */
    @Override
    public String getDictionaryValue(Integer id) {
        Dictionary dictionary;
        Cache cache = cacheManager.getCache(CacheConstants.DICTIONARY);
        Cache.ValueWrapper valueWrapper = cache.get(id);
        if (valueWrapper == null) {
            dictionary = getById(id);
            cache.put(id, dictionary);
        } else {
            dictionary = (Dictionary) valueWrapper.get();
        }
        if (dictionary == null) {
            throw new BaseInfoException(BaseInfoError.DICTIONARY_NOT_EXIST);
        }
        return dictionary.getValue();
    }

    /**
     * @param dictionaryDto
     * @return
     */
    @FullCommonField
    @Override
    public boolean save(DictionaryDto dictionaryDto) {
        Dictionary dictionary = CommonUtils.copyProperties(dictionaryDto, Dictionary.class);
        if (save(dictionary)) {
            return true;
        }
        throw new BaseInfoException(BaseInfoError.DICTIONARY_SAVE_FAIL);
    }

    /**
     * 更新
     *
     * @param dictionaryDto
     * @return
     */
    @FullCommonField(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(DictionaryDto dictionaryDto) {
        Dictionary dictionary = CommonUtils.copyProperties(dictionaryDto, Dictionary.class);
        return updateById(dictionary);
    }

    /**
     * 查询
     *
     * @param dictionary
     * @return
     */
    @Override
    public List<Dictionary> queryDictionary(Dictionary dictionary) {
        return baseMapper.queryDictionaryValue(dictionary);
    }
}
