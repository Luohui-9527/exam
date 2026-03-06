package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.dto.DictionaryDto;
import exam.demo.modulebaseinfo.pojo.model.Dictionary;

import java.util.List;

/**
 * 字典服务接口
 * 提供字典的增删改查功能
 *
 * @author luohui
 */
public interface DictionaryService extends IService<Dictionary> {
    /**
     * 根据字典ID列表获取字典值列表
     *
     * @param idList 字典ID列表
     * @return 字典值列表
     */
    List<String> getDictionary(List<Long> idList);

    /**
     * 根据字典ID获取字典值
     *
     * @param id 字典ID
     * @return 字典值
     */
    String getDictionaryValue(Long id);

    /**
     * 保存字典
     *
     * @param dictionaryDto 字典信息
     * @return 是否保存成功
     */
    boolean save(DictionaryDto dictionaryDto);

    /**
     * 更新字典
     *
     * @param dictionaryDto 字典信息
     * @return 是否更新成功
     */
    boolean update(DictionaryDto dictionaryDto);

    /**
     * 查询字典列表
     *
     * @param dictionary 查询条件
     * @return 字典列表
     */
    List<Dictionary> queryDictionary(Dictionary dictionary);
}
