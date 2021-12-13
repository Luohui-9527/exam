package exam.demo.moduleexam.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.publish.ExamPublishRecordDeleteFormDTO;
import exam.demo.moduleexam.pojo.DTO.publish.ExamPublishRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ExamPublishRecordMapper extends BaseMapper<ExamPublishRecord> {


    List<ExamPublishRecord> queryAnswerRecord(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO);

    List<ExamPublishRecord> queryExamPubulishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO);

    List<ExamPublishRecord> queryGrade(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO);

    int deleteExamPublishRecord(ExamPublishRecordDeleteFormDTO examPublishRecordDeleteFormDTO);

    @Select("SELECT id FROM exam_publish_record WHERE paper_id = #{id} ")
    List<ExamPublishRecord> listByPaperId(@Param("id") long id);
}