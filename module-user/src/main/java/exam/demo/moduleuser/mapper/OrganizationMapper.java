package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织机构表 - 数据访问接口
 *
 * @author gpmscloud
 */
public interface OrganizationMapper extends BaseMapper<Organization> {


    String getById(@Param("id") long id);

    /**
     * 模糊查询及查询全部记录
     *
     * @param organization 组织机构查询条件
     * @return 查询符合条件的组织机构记录集合
     */
    List<Organization> query(Organization organization);
}
