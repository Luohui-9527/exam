package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Department;
import exam.demo.moduleuser.pojo.model.TreeList;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface DepartmentMapper extends BaseMapper<Department> {


    List<Department> listByCompanyId(@Param("id") long id);

    /**
     * 根据Id更新部门，判别Version和DepartmentId
     *
     * @param department 部门信息
     * @return 更新成功记录数
     */
    int update(Department department);

    /**
     * 模糊查询
     *
     * @param department 查询条件
     * @return 符合条件的部门记录
     */

    List<Department> query(Department department);

    /**
     * 去重查询部门等级
     *
     * @return 部门等级
     */
    List<Department> queryLevel();

    /**
     * 根据等级排序查询id及name
     *
     * @return 符合条件的部门记录集合
     */
    List<Department> queryParent();

    /**
     * 查询部门记录输出树
     *
     * @return treelist记录
     */
    List<TreeList> getQueryListData(Long judgeId);

    /**
     * 删除列表
     *
     * @return 删除数量
     */
    @DeleteProvider(type = DepartmentProvider.class, method = "batchDelete")
    int deleteByIdList(List<Department> departments);

    class DepartmentProvider {
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Department> departments = (List<Department>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM department WHERE ");
            for (int i = 0; i < departments.size(); i++) {
                sb.append("(id = ").append(departments.get(i).getId()).append(" AND ")
                        .append("version = ").append(departments.get(i).getVersion());
                if (departments.get(i).getJudgeId() != null) {
                    sb.append(" AND ").append("company_id = ").append(departments.get(i).getJudgeId()).append(")");
                } else {
                    sb.append(")");
                }
                if (i != departments.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
