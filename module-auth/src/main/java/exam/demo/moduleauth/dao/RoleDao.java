package exam.demo.moduleauth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

    @Select("SELECT id, name FROM role WHERE id = #{id}")
    Role selectId(long id);
}
