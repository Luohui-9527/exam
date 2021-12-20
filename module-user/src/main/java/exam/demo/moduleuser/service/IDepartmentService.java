package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.DepartmentDto;
import exam.demo.moduleuser.pojo.model.Department;
import exam.demo.moduleuser.pojo.model.TreeList;

import java.util.List;

/**
 * 部门表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IDepartmentService extends IService<Department> {
    /**
     * 功能描述
     *
     * @param departmentDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(DepartmentDto departmentDto);

    /**
     * 功能描述
     *
     * @param departmentDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(DepartmentDto departmentDto);

    /**
     * 功能描述
     *
     * @param departmentDtoList
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean delete(List<DepartmentDto> departmentDtoList);

    /**
     * 功能描述
     *
     * @param department
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Department>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Department> query(Department department);

    /**
     * 功能描述
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Department>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Department> queryLevel();

    /**
     * 功能描述
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Department>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Department> queryParent();

    /**
     * 功能描述
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.TreeList>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<TreeList> queryTreeData();
}
