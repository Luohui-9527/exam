package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.pojo.model.SubjectAnswer;

import java.util.List;

/**
 * 题目答案服务接口
 * 提供题目的答案管理功能
 *
 * @author luohui
 */
public interface SubjectAnswerService extends IService<SubjectAnswer> {
    /**
     * 批量删除指定题目ID的答案
     *
     * @param subjectIdList 题目ID列表
     */
    void removeBatchBySubjectId(List<Long> subjectIdList);

    /**
     * 删除指定题目ID的答案
     *
     * @param id 题目ID
     * @return 是否删除成功
     */
    boolean removeBySubjectId(long id);

    /**
     * 根据题目ID查询答案列表
     *
     * @param subjectId 题目ID
     * @return 答案列表
     */
    List<SubjectAnswer> listAnswerBySubjectId(long subjectId);

    /**
     * 根据题目ID列表查询答案列表
     *
     * @param subjectList 题目ID列表
     * @return 答案列表
     */
    List<SubjectAnswer> listAnswer(List<Long> subjectList);
}
