package exam.demo.modulepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 试卷试题表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface PaperSubjectMapper extends BaseMapper<PaperSubject> {
    /**
     * 根据试卷id获取试题
     *
     * @param list
     * @return
     */
    List<PaperSubject> listSubjectByPaperIdList(@Param("ids") List<Long> list);
}
