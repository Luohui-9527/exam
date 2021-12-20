package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.CompanyDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.pojo.model.Company;

import java.util.List;

/**
 * 公司表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface ICompanyService extends IService<Company> {
    /**
     * 功能描述
     *
     * @param companyDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(CompanyDto companyDto);

    /**
     * 功能描述
     *
     * @param companyDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(CompanyDto companyDto);

    /**
     * 功能描述
     *
     * @param companyDto
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Company>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Company> queryCompany(CompanyDto companyDto);

    /**
     * 功能描述
     *
     * @param id
     * @return: java.util.List<exam.demo.moduleuser.dto.TreeListDto>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<TreeListDto> getCompanyTree(Long id);

    /**
     * 功能描述
     *
     * @param id
     * @return: java.lang.String
     * @Author: luohui
     * @date: 2021-12-9
     */
    String getNameById(Long id);

    /**
     * 功能描述
     *
     * @param orgId
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Company>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Company> listByOrgId(Long orgId);
}
