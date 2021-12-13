package exam.demo.server.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.server.dto.CombExamConfigDto;
import exam.demo.server.pojo.model.CombExamConfig;

import java.util.List;

/**
 * auto generate
 *
 * @author luohui
 */
public interface CombExamConfigService extends IService<CombExamConfig> {
    /**
     * 保存组卷配置
     *
     * @param combExamConfigDto
     * @return
     */
    boolean saveCombExamConfigItem(CombExamConfigDto combExamConfigDto);

    /**
     * 删除组卷配置
     *
     * @param dtoList
     * @return
     */
    boolean deleteCombExamConfig(List<CombExamConfigDto> dtoList);

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    boolean updateCombExamConfig(CombExamConfigDto dto);

    /**
     * @param combExamConfig
     * @return
     */
    List<CombExamConfig> listById(CombExamConfig combExamConfig);

}
