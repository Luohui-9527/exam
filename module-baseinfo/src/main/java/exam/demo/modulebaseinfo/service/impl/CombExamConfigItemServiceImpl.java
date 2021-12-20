package exam.demo.modulebaseinfo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulebaseinfo.dao.CombExamConfigItemDao;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import exam.demo.modulebaseinfo.service.CombExamConfigItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 */
@Service
public class CombExamConfigItemServiceImpl extends ServiceImpl<CombExamConfigItemDao, CombExamConfigDetail> implements CombExamConfigItemService {
    /**
     * 根据组卷配置删除配置项
     *
     * @param configId
     * @return
     */
    @Override
    public boolean removeByConfig(long configId) {
        QueryWrapper<CombExamConfigDetail> wrapper = new QueryWrapper<>();
        wrapper.eq(CombExamConfigDetail.COMB_EXAM_CONFIG_ID, configId);
        return remove(wrapper);
    }

    /**
     * 根据组卷配置id查询对应的配置明细
     *
     * @param item
     * @return
     */
    @Override
    public List<CombExamConfigDetail> listByCombExamId(CombExamConfigDetail item) {
        return baseMapper.listByConfigId(item);
    }
}
