package exam.demo.modulepaper.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulepaper.pojo.model.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Mapper
public interface PaperDao extends BaseMapper<Paper> {
    Object select(@Param("sql") String sql);
}
