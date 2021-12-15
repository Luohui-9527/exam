package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.server.biz.dao.SubjectAnswerDao;
import exam.demo.server.biz.service.SubjectAnswerService;
import exam.demo.server.pojo.model.SubjectAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 */
@Service
public class SubjectAnswerServiceImpl extends ServiceImpl<SubjectAnswerDao, SubjectAnswer> implements SubjectAnswerService {
    /**
     * @param subjectIdList
     * @return
     */
    @Override
    public void removeBatchBySubjectId(List<Integer> subjectIdList) {
//        Map<String,Object> map = new HashMap<>();
//        for (Integer id : subjectIdList) {
//            map.put("subject_id",id);
//        }
//        removeByMap(map);
        baseMapper.removeBatchBySubjectId(subjectIdList);
    }

    @Override
    public boolean removeBySubjectId(long id) {
        return baseMapper.removeBySubjectId(id);
    }


    @Override
    public List<SubjectAnswer> listAnswerBySubjectId(long subjectId) {
        QueryWrapper<SubjectAnswer> wrapper = new QueryWrapper<>();
        wrapper.eq(SubjectAnswer.SUBJECT_ID, subjectId);
        return list(wrapper);
    }

    @Override
    public List<SubjectAnswer> listAnswer(List<Integer> subjectList) {
        return baseMapper.querySubjectAnswerBySubjectId(subjectList);
    }
}
