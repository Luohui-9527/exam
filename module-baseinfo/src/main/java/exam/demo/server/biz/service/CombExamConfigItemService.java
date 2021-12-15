package exam.demo.server.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.server.pojo.model.CombExamConfigDetail;

import java.util.List;


public interface CombExamConfigItemService extends IService<CombExamConfigDetail> {
    /**
     * 根据组卷配置删除配置项
     *
     * @param configId
     * @return
     */
    boolean removeByConfig(long configId);

    /**
     * 根据组卷配置id查询对应的配置明细
     *
     * @param item
     * @return
     */
    List<CombExamConfigDetail> listByCombExamId(CombExamConfigDetail item);

}
