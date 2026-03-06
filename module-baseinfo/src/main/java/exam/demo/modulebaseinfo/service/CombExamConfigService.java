package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.dto.CombExamConfigDto;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfig;

import java.util.List;

/**
 * 组卷配置服务接口
 * 提供组卷配置的增删改查功能
 *
 * @author luohui
 */
public interface CombExamConfigService extends IService<CombExamConfig> {
    /**
     * 保存组卷配置及配置项
     *
     * @param combExamConfigDto 组卷配置信息
     * @return 是否保存成功
     */
    boolean saveCombExamConfigItem(CombExamConfigDto combExamConfigDto);

    /**
     * 删除组卷配置及配置项
     *
     * @param dtoList 待删除的组卷配置列表
     * @return 是否删除成功
     */
    boolean deleteCombExamConfig(List<CombExamConfigDto> dtoList);

    /**
     * 更新组卷配置及配置项
     *
     * @param dto 组卷配置信息
     * @return 是否更新成功
     */
    boolean updateCombExamConfig(CombExamConfigDto dto);

    /**
     * 根据条件查询组卷配置列表
     *
     * @param combExamConfig 查询条件
     * @return 组卷配置列表
     */
    List<CombExamConfig> listById(CombExamConfig combExamConfig);

}
