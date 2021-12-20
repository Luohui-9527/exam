package exam.demo.modulepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulepaper.mapper.PaperSubjectAnswerMapper;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;
import exam.demo.modulepaper.service.IPaperSubjectAnswerService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷答案表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class PaperSubjectAnswerServiceImpl extends ServiceImpl<PaperSubjectAnswerMapper, PaperSubjectAnswer> implements IPaperSubjectAnswerService {
    /**
     * 通过试题id集合来获取一批答案
     *
     * @param list
     * @return
     */
    @Override
    public List<PaperSubjectAnswer> listAnswerBySubjectIdList(List<Long> list) {
        return baseMapper.batchQueryAnswerBySubjectId(list);
    }

    /**
     * 通过试题Id删除答案
     *
     * @param idList
     * @return
     */
    @Override
    public boolean deleteBySubjectId(List<Long> idList) {
        return baseMapper.deleteBySubjectIdList(idList);
    }
}
