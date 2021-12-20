package exam.demo.modulepaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulepaper.pojo.model.PaperSubject;

import java.util.List;

/**
 * 试卷试题表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IPaperSubjectService extends IService<PaperSubject> {
    /**
     * 通过试卷Id获取试题
     *
     * @param paperId
     * @return
     */
    List<PaperSubject> listSubjectByPaperId(long paperId);

    /**
     * 通过试卷d集合来获取一批试题
     *
     * @param list
     * @return
     */
    List<PaperSubject> listSubjectByPaperIdList(List<Long> list);
}
