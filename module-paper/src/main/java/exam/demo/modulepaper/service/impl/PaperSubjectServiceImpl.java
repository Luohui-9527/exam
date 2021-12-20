package exam.demo.modulepaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.modulepaper.mapper.PaperSubjectMapper;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import exam.demo.modulepaper.service.IPaperSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 试卷试题表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class PaperSubjectServiceImpl extends ServiceImpl<PaperSubjectMapper, PaperSubject> implements IPaperSubjectService {
    /**
     * 通过试卷Id获取试题
     *
     * @param paperId
     * @return
     */
    @Override
    public List<PaperSubject> listSubjectByPaperId(long paperId) {
        QueryWrapper<PaperSubject> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.PAPER_ID, paperId);
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
