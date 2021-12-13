package exam.demo.moduleauth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-17
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
}
