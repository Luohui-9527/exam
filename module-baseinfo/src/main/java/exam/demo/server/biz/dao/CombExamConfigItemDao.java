package exam.demo.server.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.server.pojo.model.CombExamConfigDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CombExamConfigItemDao extends BaseMapper<CombExamConfigDetail> {

    /**
     * 通过组卷配置id查询明细
     *
     * @param item
     * @return
     */
    @Select("SELECT a.*,b.name AS subjectType,c.name AS category,d.value AS difficultyName FROM comb_exam_config_item a,subject_type b,category c,dictionary d WHERE " +
            "a.subject_type_id = b.id AND a.category_id = c.id AND a.difficulty = d.id AND a.comb_exam_config_id = #{combExamId} ")
    List<CombExamConfigDetail> listByConfigId(CombExamConfigDetail item);
}
