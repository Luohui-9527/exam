package exam.demo.modulepaper.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulepaper.biz.dao.PaperSubjectAnswerDao;
import exam.demo.modulepaper.biz.service.PaperSubjectAnswerService;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Service
public class PaperSubjectAnswerServiceImpl extends ServiceImpl<PaperSubjectAnswerDao, PaperSubjectAnswer> implements PaperSubjectAnswerService {
    /**
     * 通过试题id集合来获取一批答案
     *
     * @param list
     * @return
     */
    @Override
    public List<PaperSubjectAnswer> listAnswerBySubjectIdList(List<Integer> list) {
        return baseMapper.batchQueryAnswerBySubjectId(list);
    }

    /**
     * 通过试题Id删除答案
     *
     * @param idList
     * @return
     */
    @Override
    public boolean deleteBySubjectId(List<Integer> idList) {
        return baseMapper.deleteBySubjectIdList(idList);
    }
}
