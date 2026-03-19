package exam.demo.modulebaseinfo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulebaseinfo.dao.CombExamConfigItemDao;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import exam.demo.modulebaseinfo.service.CombExamConfigItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组卷配置项服务实现类
 *
 * @author luohui
 */
@Service
public class CombExamConfigItemServiceImpl extends ServiceImpl<CombExamConfigItemDao, CombExamConfigDetail> implements CombExamConfigItemService {
    /**
     * 根据组卷配置删除配置项
     *
     * @param configId 组卷配置ID
     * @return 是否删除成功
     */
    @Override
    public boolean removeByConfig(long configId) {
        QueryWrapper<CombExamConfigDetail> wrapper = new QueryWrapper<>();
        wrapper.eq(CombExamConfigDetail.COMB_EXAM_CONFIG_ID, configId);
        return remove(wrapper);
    }

    /**
     * 根据组卷配置ID查询对应的配置明细
     *
     * @param configId 组卷配置ID
     * @return 配置明细列表
     */
    @Override
    public List<CombExamConfigDetail> listByConfigId(Long configId) {
        return baseMapper.listByConfigId(configId);
    }
}
