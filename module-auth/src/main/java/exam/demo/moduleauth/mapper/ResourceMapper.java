package exam.demo.moduleauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface ResourceMapper extends BaseMapper<Resource> {


    List<Resource> listByIdList(@Param("ids") List<Long> ids);
}
