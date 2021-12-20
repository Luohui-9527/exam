package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 公司表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface CompanyMapper extends BaseMapper<Company> {
    List<Company> listByOrgId(@Param("id") Long id);

    String selectNameById(Long id);

    /**
     * 模糊查询
     *
     * @param company 查询条件
     * @return 符合条件的公司记录
     */

    List<Company> query(Company company);

    /**
     * 查询树
     *
     * @return 树的相关数据
     */

    List<Map> getQueryListData(Long judgeId);
}
