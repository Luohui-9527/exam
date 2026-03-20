package exam.demo.modulebaseinfo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CombExamConfigItemDao extends BaseMapper<CombExamConfigDetail> {

    /**
     * 通过组卷配置ID查询明细
     *
     * @param configId 组卷配置ID
     * @return 组卷配置明细列表
     */
    @Select("SELECT a.*,b.name AS subjectType,c.name AS category,d.value AS difficultyName FROM comb_exam_config_item a,subject_type b,category c,dictionary d WHERE " +
            "a.subject_type_id = b.id AND a.category_id = c.id AND a.difficulty = d.id AND a.comb_exam_config_id = #{configId}")
    List<CombExamConfigDetail> listByConfigId(@Param("configId") String configId);
}
