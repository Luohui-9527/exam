package exam.demo.modulepaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;

import java.util.List;

/**
 * 试卷答案表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IPaperSubjectAnswerService extends IService<PaperSubjectAnswer> {
    /**
     * 通过试题id集合来获取一批答案
     *
     * @param list
     * @return
     */
    List<PaperSubjectAnswer> listAnswerBySubjectIdList(List<Long> list);

    /**
     * 通过试题Id删除答案
     *
     * @param idList
     * @return
     */
    boolean deleteBySubjectId(List<Long> idList);
}
