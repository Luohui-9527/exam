package exam.demo.moduleuser.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.OrganizationDto;
import exam.demo.moduleuser.pojo.model.Organization;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
public interface OrganizationService extends IService<Organization> {
    /**
     * 功能描述
     *
     * @param organizationDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(OrganizationDto organizationDto);

    /**
     * 功能描述
     *
     * @param organizationDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(OrganizationDto organizationDto);

    /**
     * 功能描述
     *
     * @param list
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean delete(List<OrganizationDto> list);

    /**
     * 功能描述
     *
     * @param organizationDto
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Organization>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Organization> list(OrganizationDto organizationDto);

    /**
     * 功能描述
     *
     * @param id
     * @return: java.lang.String
     * @Author: luohui
     * @date: 2021-12-9
     */
    String getNameById(long id);
}
