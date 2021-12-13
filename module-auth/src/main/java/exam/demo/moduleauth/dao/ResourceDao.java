package exam.demo.moduleauth.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.Resource;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface ResourceDao extends BaseMapper<Resource> {
    @Select("<script>" +
            "SELECT * FROM resource WHERE id IN " +
            "<foreach collection=\"ids\" item=\"id\" separator=\",\" close=\")\" open=\"(\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    List<Resource> listByIdList(@Param("ids") List<Long> ids);

}
