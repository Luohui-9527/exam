package exam.demo.moduleexam.service;

import exam.demo.moduleexam.pojo.DTO.report.ExamDetailQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.model.ExamRecord;

import java.util.List;

public interface ReportService {
    /**
     * 获取所有考试发布记录
     *
     * @param examReportRecordQueryFormDTO
     * @return list<ExamReportRecordExamTableDataDTO>
     */
    List<ExamReportRecordExamTableDataDTO> query(ExamReportRecordQueryFormDTO examReportRecordQueryFormDTO);

    /**
     * 获取对应每场发布记录的考试情况
     *
     * @param examDetailQueryFormDTO
     * @return
     */
    List<ExamReportRecordDetailTableDataDTO> queryDetail(ExamDetailQueryFormDTO examDetailQueryFormDTO);

    /**
     * 分析考试成绩
     *
     * @param examRecords
     * @return String 评价
     */
    String analysisScore(List<ExamRecord> examRecords);

    /**
     * 根据考试发布Id查询对应试卷满分
     *
     * @param examId
     * @return
     */
    Double findMaxScore(long examId);
}
