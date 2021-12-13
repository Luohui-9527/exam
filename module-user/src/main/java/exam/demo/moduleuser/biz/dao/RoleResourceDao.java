package exam.demo.moduleuser.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.RoleResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface RoleResourceDao extends BaseMapper<RoleResource> {

    @Delete("<script>" +
            "DELETE FROM role_resource WHERE role_id IN " +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    boolean removeBatch(@Param("ids") List<Long> ids);
}
