package exam.demo.modulepaper.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulepaper.biz.dao.PaperSubjectDao;
import exam.demo.modulepaper.biz.service.PaperSubjectService;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Service
public class PaperSubjectServiceImpl extends ServiceImpl<PaperSubjectDao, PaperSubject> implements PaperSubjectService {
    /**
     * 通过试卷Id获取试题
     *
     * @param paperId
     * @return
     */
    @Override
    public List<PaperSubject> listSubjectByPaperId(long paperId) {
        QueryWrapper<PaperSubject> wrapper = new QueryWrapper<>();
        wrapper.eq(PaperSubject.PAPER_ID, paperId);
        return list(wrapper);
    }

    /**
     * 通过试卷d集合来获取一批试题
     *
     * @param list
     * @return
     */
    @Override
    public List<PaperSubject> listSubjectByPaperIdList(List<Long> list) {
        return baseMapper.listSubjectByPaperIdList(list);
    }
}
