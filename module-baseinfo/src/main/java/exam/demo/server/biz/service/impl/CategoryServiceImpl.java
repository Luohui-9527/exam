package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.CacheConstants;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.server.biz.dao.CategoryDao;
import exam.demo.server.biz.service.CategoryService;
import exam.demo.server.dto.CategoryDto;
import exam.demo.server.exception.BaseInfoError;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.dto.TreeList;
import exam.demo.server.pojo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Autowired
    CacheManager cacheManager;

    /**
     * 保存，为了使用切面进行公共字段填充
     *
     * @param dto
     * @return
     */
    @FullCommonField
    @Override
    public boolean save(CategoryDto dto) {
        Category category = CommonUtils.copyProperties(dto, Category.class);
        return save(category);
    }

    /**
     * 更新，为了使用切面进行公共字段填充
     *
     * @param dto
     * @return
     */
    @FullCommonField(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(CategoryDto dto) {
        Category category = CommonUtils.copyProperties(dto, Category.class);
        try {
            return updateById(category);
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.CATEGORY_UPDATE_FAIL);
        }
    }

    /**
     * 根据名称和父节点列出题目类型
     *
     * @param category
     * @return
     */
    @Override
    public List<Category> listByName(Category category) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(category.getName())) {
            wrapper.likeRight(Category.NAME, category.getName());
        }
        if (category.getParentId() != null) {
            wrapper.eq(Category.PARENT_ID, category.getParentId());
        }
        wrapper.eq(Category.ORG_ID, category.getOrgId());
        wrapper.orderByDesc(Category.UPDATE_TIME);
        return list(wrapper);
    }

    /**
     * 根据OrgId和ParentId获取
     *
     * @param category
     * @return
     */
    @Override
    public List<TreeList> getTree(Category category) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq(Category.ORG_ID, category.getOrgId());
        categoryQueryWrapper.orderByDesc(Category.PARENT_ID);
        List<Category> categoryList = list(categoryQueryWrapper);
        return CommonUtils.convertList(categoryList, TreeList.class);
    }

    /**
     * 根据Id获取题目类型
     *
     * @param categoryIdList
     * @return
     */
    @Override
    public List<String> getCategoryName(List<Long> categoryIdList) {
        Cache cache = cacheManager.getCache(CacheConstants.CATEGORY);
        List<String> res = new ArrayList<>();
        for (Long id : categoryIdList) {
            Cache.ValueWrapper wrapper = cache.get(id);
            Category category;
            if (wrapper == null) {
                category = getById(id);
                cache.put(id, category);
            } else {
                category = (Category) wrapper.get();
            }
            if (category == null) {
                throw new BaseInfoException(BaseInfoError.CATEGORY_NOT_EXIST);
            }
            res.add(category.getName());
        }
        return res;
    }

    /**
     * 根据节点id查询子节点比如c++ -> c++基础 + c++进阶
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Category> queryChildNode(Long parentId) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        return list(wrapper);
    }

    @Override
    public String getCategoryNameById(Long id) {
        Cache cache = cacheManager.getCache(CacheConstants.CATEGORY_VAL);
        Cache.ValueWrapper wrapper = cache.get(id);
        if (wrapper == null) {
            QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("name");
            queryWrapper.eq("id", id);
            Category category = getOne(queryWrapper);
            if (category == null) {
                throw new BaseInfoException(BaseInfoError.CATEGORY_NOT_EXIST);
            }
            cache.put(id, category.getName());
            return category.getName();
        } else {
            return (String) wrapper.get();
        }
    }
}
