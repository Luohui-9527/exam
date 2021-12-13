package exam.demo.moduleuser.biz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Mapper
public interface CompanyDao extends BaseMapper<Company> {

    @Select("SELECT * FROM company WHERE org_id = #{id}")
    List<Company> listByOrgId(@Param("id") Long id);

    @Select("SELECT name FROM company WHERE id = #{id}")
    String selectNameById(Long id);

    /**
     * 模糊查询
     *
     * @param company 查询条件
     * @return 符合条件的公司记录
     */
    @Select("<script>" +
            "SELECT a.*,b.name AS orgName FROM company a " +
            "LEFT JOIN organization b ON a.org_id = b.id" +
            "<where>" +
            "<if test=\"name!=null and name!=''\">" +
            "AND a.name LIKE CONCAT(#{name},'%')" +
            "</if>" +
            "<if test=\"orgId!=null and orgId!=''\">" +
            "AND a.org_id = #{orgId}" +
            "</if>" +
            "AND (a.id = #{judgeId} OR a.org_id = #{judgeId}) " +
            "</where>" +
            "ORDER BY a.updated_time DESC" +
            "</script>")
    List<Company> query(Company company);

    /**
     * 查询树
     *
     * @return 树的相关数据
     */
//    @Results(id = "tree",value = {
//            @Result(property = "id", column = "id", id = true),
//            @Result(property = "name", column = "name"),
//            @Result(property = "parent_id", column = "org_id"),
//            @Result(property = "version", column = "version")
//    })
//    @ResultMap("tree")
    @Select("<script>" +
            "SELECT id,name,org_id as parent_id,version from company " +
            "WHERE id = #{judgeId} OR org_id = #{judgeId} " +
            "UNION " +
            "SELECT id,name,null as parent_id,version FROM organization " +
            "ORDER BY parent_id" +
            "</script>")
    List<Map> getQueryListData(Long judgeId);
}
