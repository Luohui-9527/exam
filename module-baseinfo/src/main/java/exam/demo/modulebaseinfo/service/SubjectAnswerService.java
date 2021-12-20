package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.pojo.model.SubjectAnswer;

import java.util.List;

/**
 * @author luohui
 */
public interface SubjectAnswerService extends IService<SubjectAnswer> {
    /**
     * @param subjectIdList
     * @return
     */
    void removeBatchBySubjectId(List<Long> subjectIdList);


    boolean removeBySubjectId(long id);


    List<SubjectAnswer> listAnswerBySubjectId(long subjectId);

    List<SubjectAnswer> listAnswer(List<Long> subjectList);
}
