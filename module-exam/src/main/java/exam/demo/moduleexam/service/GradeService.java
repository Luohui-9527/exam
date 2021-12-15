package exam.demo.moduleexam.service;


import exam.demo.moduleexam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.grade.ExamGradeRecordTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.grade.MarkingPaperDTO;
import exam.demo.moduleexam.pojo.DTO.grade.MyAnswerDTO;

import java.util.List;

public interface GradeService {
    /**
     * 查询阅卷记录信息
     *
     * @param examGradeRecordQueryFormDTO
     * @return
     */
    List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO);

    /**
     * 添加试卷评分信息
     *
     * @param markingPaperDTO
     * @return
     */
    Boolean markingPaper(MarkingPaperDTO markingPaperDTO);

    /**
     * 得到我的答案和标准答案
     *
     * @param examRecordId
     * @return 我的答案和标准答案
     */
    List<MyAnswerDTO> getMyAnswer(Integer examRecordId);
}
