package exam.demo.moduleexam.service;


import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;

import java.util.List;

public interface AnswerSheetRecordService {
    /**
     * 获取答卷记录
     *
     * @param examAnswerSheetRecordQueryFormDTO
     * @return
     */
    List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO);
}
