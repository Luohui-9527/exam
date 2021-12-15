package exam.demo.server.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.server.pojo.model.SubjectAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectAnswerDao extends BaseMapper<SubjectAnswer> {
    boolean removeBatchBySubjectId(@Param(value = "idList") List<Integer> subjectIdList);


    boolean removeBySubjectId(@Param(value = "id") long id);

    /**
     * 通过题目id查询答案
     *
     * @param subjectIdList
     * @return
     */
    @Select("<script> SELECT id,subject_id,answer,right_answer FROM subject_answer WHERE subject_id IN " +
            "<foreach item = 'subjectId' index = 'index' collection = 'subjectIdList' open='(' separator=',' close=')'>" +
            "#{subjectId} </foreach> </script>")
    List<SubjectAnswer> querySubjectAnswerBySubjectId(@Param("subjectIdList") List<Integer> subjectIdList);
}
