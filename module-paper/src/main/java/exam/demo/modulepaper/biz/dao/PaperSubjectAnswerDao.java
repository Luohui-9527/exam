package exam.demo.modulepaper.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Mapper
public interface PaperSubjectAnswerDao extends BaseMapper<PaperSubjectAnswer> {
    /**
     * 根据试题Id查询答案
     *
     * @param ids
     * @return
     */
    List<PaperSubjectAnswer> batchQueryAnswerBySubjectId(@Param("ids") List<Long> ids);

    /**
     * 通过试题id删除答案
     *
     * @param ids
     * @return
     */
    boolean deleteBySubjectIdList(@Param("ids") List<Long> ids);
}
