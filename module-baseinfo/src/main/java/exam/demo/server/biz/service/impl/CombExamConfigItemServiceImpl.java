package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.server.biz.dao.CombExamConfigItemDao;
import exam.demo.server.biz.service.CombExamConfigItemService;
import exam.demo.server.pojo.model.CombExamConfigItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 */
@Service
public class CombExamConfigItemServiceImpl extends ServiceImpl<CombExamConfigItemDao, CombExamConfigItem> implements CombExamConfigItemService {
    /**
     * 根据组卷配置删除配置项
     *
     * @param configId
     * @return
     */
    @Override
    public boolean removeByConfig(long configId) {
        QueryWrapper<CombExamConfigItem> wrapper = new QueryWrapper<>();
        wrapper.eq(CombExamConfigItem.COMB_EXAM_CONFIG_ID, configId);
        return remove(wrapper);
    }

    /**
     * 根据组卷配置id查询对应的配置明细
     *
     * @param item
     * @return
     */
    @Override
    public List<CombExamConfigItem> listByCombExamId(CombExamConfigItem item) {
        return baseMapper.listByConfigId(item);
    }
}
