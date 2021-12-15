package exam.demo.moduleauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    List<Resource> listByIdList(@Param("ids") List<Integer> ids);

}
