package exam.demo.server.biz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.server.pojo.model.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
