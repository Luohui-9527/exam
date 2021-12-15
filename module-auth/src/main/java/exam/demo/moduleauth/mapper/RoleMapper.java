package exam.demo.moduleauth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Role selectId(long id);
}
