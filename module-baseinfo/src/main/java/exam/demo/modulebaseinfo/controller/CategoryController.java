package exam.demo.modulebaseinfo.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulebaseinfo.constant.ControllerConstant;
import exam.demo.modulebaseinfo.dto.CategoryDto;
import exam.demo.modulebaseinfo.exception.BaseInfoError;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.pojo.dto.TreeList;
import exam.demo.modulebaseinfo.pojo.model.Category;
import exam.demo.modulebaseinfo.pojo.vo.CategoryListVo;
import exam.demo.modulebaseinfo.pojo.vo.CategoryQueryVo;
import exam.demo.modulebaseinfo.pojo.vo.CategoryVo;
import exam.demo.modulebaseinfo.pojo.vo.TreeListVo;
import exam.demo.modulebaseinfo.service.CategoryService;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@RequestMapping(ControllerConstant.CATEGORY)
@RestController
public class CategoryController {
    @Autowired
    CommonState commonState;

    @Autowired
    CategoryService categoryService;

    /**
     * 保存字典值
     *
     * @param categoryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.SAVE_CATEGORY)
    public CommonResponse<Boolean> saveCategory(@RequestBody CategoryVo categoryVo) {
        CategoryDto category = CommonUtils.copyProperties(categoryVo, CategoryDto.class);
        if (categoryService.save(category)) {
            return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(commonState.getVersion(), commonState.FAIL, commonState.FAIL_MSG, false);
    }

    /**
     * 删除题目类别
     *
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.DELETE_CATEGORY)
    public CommonResponse<Boolean> deleteCategory(@RequestBody List<CategoryVo> categoryVoList) {
        List<Category> categoryList = CommonUtils.convertList(categoryVoList, Category.class);
        for (Category category : categoryList) {
            category.setOrgId(TokenUtils.getUser().getOrgId());
        }
        try {
            categoryService.removeByIds(categoryList);
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.CATEGORY_DEL_FAIL);
        }
        return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, true);
    }

    /**
     * 更新字典值
     *
     * @param categoryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.UPDATE_CATEGORY)
    public CommonResponse<Boolean> updateCategory(@RequestBody CategoryVo categoryVo) {
        CategoryDto dto = CommonUtils.copyProperties(categoryVo, CategoryDto.class);
        dto.setOrgId(TokenUtils.getUser().getOrgId());
        dto.setOldVersion(dto.getVersion());
        categoryService.update(dto);
        return new CommonResponse<>(commonState.SUCCESS, commonState.SUCCESS_MSG, true);
    }

    /**
     * 分页查询字典值
     *
     * @param queryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_CATEGORY)
    public CommonResponse<PageVo<CategoryListVo>> queryCategory(@RequestBody CategoryQueryVo queryVo) {
        Page<CategoryQueryVo> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        Category category = new Category();
        category.setOrgId(TokenUtils.getUser().getOrgId());
        category.setName(queryVo.getName());
        category.setParentId(queryVo.getParentId());
        List<Category> categoryList = categoryService.listByName(category);
        List<CategoryListVo> categoryListVoList = CommonUtils.convertList(categoryList, CategoryListVo.class);
        PageVo<CategoryListVo> pageVo = PageMapUtil.getPageMap(categoryListVoList, page);
        return new CommonResponse<>(commonState.SUCCESS, commonState.SUCCESS_MSG, pageVo);
    }

    /**
     * 查询题目类型数据
     *
     * @param categoryQueryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_CATEGORY_INFO)
    public CommonResponse<List> queryCategoryInfo(@RequestBody CategoryQueryVo categoryQueryVo) {
        Category category = CommonUtils.copyProperties(categoryQueryVo, Category.class);
        category.setOrgId(TokenUtils.getUser().getOrgId());
        List<Category> categoryList = categoryService.listByName(category);
        List<CategoryListVo> res = CommonUtils.convertList(categoryList, CategoryListVo.class);
        return new CommonResponse<>(commonState.SUCCESS, commonState.SUCCESS_MSG, res);
    }

    /**
     * 查询题目类型树
     *
     * @return
     */
    @MethodEnhancer
    @GetMapping(ControllerConstant.QUERY_CATEGORY_TREE)
    public CommonResponse<List> queryCategoryTree() {
        Category category = new Category();
        category.setOrgId(TokenUtils.getUser().getOrgId());
        List<TreeList> treeListList = categoryService.getTree(category);
        List<TreeListVo> res = CommonUtils.convertList(treeListList, TreeListVo.class);
        return new CommonResponse<>(commonState.SUCCESS, commonState.SUCCESS_MSG, res);
    }
}
