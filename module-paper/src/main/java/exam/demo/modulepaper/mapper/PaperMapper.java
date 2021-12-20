package exam.demo.modulepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulepaper.pojo.model.Paper;
import org.apache.ibatis.annotations.Param;

/**
 * 试卷表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface PaperMapper extends BaseMapper<Paper> {

    Object select(@Param("sql") String sql);
}
