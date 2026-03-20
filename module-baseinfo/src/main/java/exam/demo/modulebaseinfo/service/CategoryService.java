package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.dto.CategoryDto;
import exam.demo.modulebaseinfo.pojo.dto.TreeList;
import exam.demo.modulebaseinfo.pojo.model.Category;

import java.util.List;

/**
 * 分类服务接口
 * 提供分类的增删改查、树形结构获取等功能
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface CategoryService extends IService<Category> {
    /**
     * 保存分类
     * 为了使用切面进行公共字段填充
     *
     * @param dto 分类信息
     * @return 是否保存成功
     */
    boolean save(CategoryDto dto);

    /**
     * 更新分类
     * 为了使用切面进行公共字段填充
     *
     * @param dto 分类信息
     * @return 是否更新成功
     */
    boolean update(CategoryDto dto);

    /**
     * 根据名称和父节点查询分类列表
     *
     * @param category 查询条件
     * @return 分类列表
     */
    List<Category> listByName(Category category);

    /**
     * 根据组织ID和父节点ID获取分类树形结构
     *
     * @param category 查询条件
     * @return 分类树形结构列表
     */
    List<TreeList> getTree(Category category);

    /**
     * 根据分类ID列表获取分类名称列表
     *
     * @param categoryIdList 分类ID列表
     * @return 分类名称列表
     */
    List<String> getCategoryName(List<String> categoryIdList);

    /**
     * 根据节点ID查询子节点
     * 例如：c++ -> c++基础 + c++进阶
     *
     * @param parentId 父节点ID
     * @return 子节点列表
     */
    List<Category> queryChildNode(String parentId);

    /**
     * 根据分类ID获取分类名称
     *
     * @param id 分类ID
     * @return 分类名称
     */
    String getCategoryNameById(String id);
}
