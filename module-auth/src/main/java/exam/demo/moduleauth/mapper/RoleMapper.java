package exam.demo.moduleauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Role;

/**
 * 权限表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface RoleMapper extends BaseMapper<Role> {
    Role selectId(long id);
}
