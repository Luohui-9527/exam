package exam.demo.modulepaper.biz.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@Mapper
public interface PaperSubjectDao extends BaseMapper<PaperSubject> {
    /**
     * 根据试卷id获取试题
     *
     * @param list
     * @return
     */
    List<PaperSubject> listSubjectByPaperIdList(@Param("ids") List<Long> list);
}
