package exam.demo.moduleuser.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.ResourceDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.pojo.model.Resource;

import java.util.List;


/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 功能描述
     *
     * @param resourceDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(ResourceDto resourceDto);

    /**
     * 功能描述
     *
     * @param resources
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean delete(List<Resource> resources);

    /**
     * 功能描述
     *
     * @param resourceDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(ResourceDto resourceDto);

    /**
     * 功能描述
     *
     * @param resourceDto
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Resource>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Resource> list(ResourceDto resourceDto);

    /**
     * 功能描述
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.dto.TreeListDto>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<TreeListDto> getQueryList();

    /**
     * 功能描述
     *
     * @param idList
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Resource>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Resource> listByIdList(List<Long> idList);
}
