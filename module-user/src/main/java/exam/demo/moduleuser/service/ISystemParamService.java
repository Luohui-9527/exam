package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.SystemParamDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.pojo.model.SystemParam;
import exam.demo.moduleuser.pojo.vo.SystemParamListVo;

import java.util.List;

/**
 * 系统配置表 - 服务接口
 *
 * @author gpmscloud
 */
public interface ISystemParamService extends IService<SystemParam> {
    boolean save(SystemParamDto systemParamDto);

    boolean delete(List<SystemParamDto> systemParamDto);

    boolean update(SystemParamDto systemParamDto);

    /**
     * 根据请求条件查询符合条件的参数记录集合
     *
     * @param systemParamDTO 请求条件查询信息
     * @return
     */
    List<SystemParamListVo> queryByCondition(SystemParamDto systemParamDTO);


    /**
     * 获取参数树集合
     *
     * @return 以树（treelist）形式返回数据
     */
    List<TreeListDto> getQueryListData();

}
