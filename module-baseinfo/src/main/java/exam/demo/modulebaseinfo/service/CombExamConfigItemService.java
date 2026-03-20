package exam.demo.modulebaseinfo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;

import java.util.List;


/**
 * 组卷配置项服务接口
 * 提供组卷配置明细的管理功能
 *
 * @author luohui
 */
public interface CombExamConfigItemService extends IService<CombExamConfigDetail> {
    /**
     * 根据组卷配置ID删除配置项
     *
     * @param configId 组卷配置ID
     * @return 是否删除成功
     */
    boolean removeByConfig(String configId);

    /**
     * 根据组卷配置ID查询对应的配置明细
     *
     * @param configId 组卷配置ID
     * @return 配置明细列表
     */
    List<CombExamConfigDetail> listByConfigId(String configId);

}
