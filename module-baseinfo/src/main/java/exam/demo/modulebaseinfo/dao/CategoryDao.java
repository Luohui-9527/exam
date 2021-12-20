package exam.demo.modulebaseinfo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulebaseinfo.pojo.model.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
