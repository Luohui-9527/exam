package exam.demo.moduleuser.biz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Resource;
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


    /**
     * 模糊查询及查询全部记录
     *
     * @param resource 资源查询条件
     * @return 查询符合条件的资源记录集合
     */
    @Select("<script>" +
            "SELECT * FROM resource " +
            "<where>" +
            "<if test=\"id!=null and id!=''\">" +
            "AND id = #{id} UNION SELECT * FROM resource WHERE parent_id = #{id}" +
            "</if>" +
            "</where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Resource> query(Resource resource);

    /**
     * 根据Id更新资源记录
     *
     * @param resource 资源更新信息
     * @return 是否更新成功
     */
    @Update("UPDATE resource SET name = #{name},code = #{code},parent_id = #{parentId},url = #{url}," +
            "open_img = #{openImg},close_img = #{closeImg},resource_type = #{resourceType},leaf = #{leaf}," +
            "updated_by = #{updatedBy},updated_time = #{updatedTime},version = #{version} \n" +
            "WHERE id = #{id} AND version = #{oldVersion}")
    int updateResource(Resource resource);

    /**
     * 获取父节点个数，判断是否为叶节点
     *
     * @param resource 资源信息
     * @return 父节点个数
     */
    @SelectProvider(type = ResourceProvider.class, method = "batchSelect")
    int getLeafCount(List<Resource> resource);

    @Delete("DELETE FROM resource WHERE id = #{id} AND version = #{version} ")
    int deleteById(Resource resource);

    @DeleteProvider(type = ResourceProvider.class, method = "batchDelete")
    int deleteByIdList(List<Resource> resources);

    @Select("<script>" +
            " SELECT id, resource_type FROM resource WHERE id IN\n" +
            "        <foreach collection=\"idList\" index=\"i\" item=\"id\" open=\"(\" close=\")\" separator=\",\">\n" +
            "            #{id}\n" +
            "        </foreach>" +
            "</script>")
    List<Resource> selectRoleByIdList(@Param("idList") List<Long> idList);

    class ResourceProvider {
        /* 查询部门是否存在下级资源 */
        public String batchSelect(Map map) {
            List<Resource> resources = (List<Resource>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM resource WHERE ");
            for (int i = 0; i < resources.size(); i++) {
                sb.append("parent_id = ").append(resources.get(i).getId());
                if (i != resources.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }

        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Resource> resources = (List<Resource>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM resource WHERE ");
            for (int i = 0; i < resources.size(); i++) {
                sb.append("(id = ").append(resources.get(i).getId()).append(" AND ")
                        .append("version = ").append(resources.get(i).getVersion()).append(")");
                if (i != resources.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
