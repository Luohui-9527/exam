package exam.demo.moduleexam.service.impl;


import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.RPCUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleexam.exception.ExamError;
import exam.demo.moduleexam.exception.ExamException;
import exam.demo.moduleexam.manage.PaperApi;
import exam.demo.moduleexam.pojo.DTO.report.ExamDetailQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordDetailTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordExamTableDataDTO;
import exam.demo.moduleexam.pojo.DTO.report.ExamReportRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.model.AnswerRecord;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import exam.demo.moduleexam.pojo.model.ExamRecord;
import exam.demo.moduleexam.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private ExamRecordService examRecordService;
    @Autowired
    private ExamPublishRecordService examPublishRecordService;
    @Autowired
    private UserRecordService userRecordService;
    @Autowired
    private AnswerRecordService answerRecordService;

    @Autowired
    PaperApi paperApi;

    @Autowired
    CommonState commonState;

    @Override
    public List<ExamReportRecordExamTableDataDTO> query(ExamReportRecordQueryFormDTO examReportRecordQueryFormDTO) {
        try {
            List<ExamPublishRecord> recordList = examPublishRecordService.getExamPublishRecordList(examReportRecordQueryFormDTO.getTitle(),
                    examReportRecordQueryFormDTO.getExamSession(), examReportRecordQueryFormDTO.getStartTime(), examReportRecordQueryFormDTO.getEndTime());
            List<ExamReportRecordExamTableDataDTO> tableDataDTOS = new ArrayList<>();
            for (ExamPublishRecord examPublishRecord : recordList) {
                ExamReportRecordExamTableDataDTO tableDataDTO = new ExamReportRecordExamTableDataDTO();
                BeanUtils.copyProperties(examPublishRecord, tableDataDTO);
                List<ExamRecord> examRecords = examRecordService.getListByExamPublishRecordId(examPublishRecord.getId());
                tableDataDTO.setActualPepoleNum(examRecords.size());
                tableDataDTOS.add(tableDataDTO);
            }
            return tableDataDTOS;
        } catch (Exception e) {
            throw new ExamException(ExamError.QUERY_ERROR);
        }
    }


    @Override
    public List<ExamReportRecordDetailTableDataDTO> queryDetail(ExamDetailQueryFormDTO examDetailQueryFormDTO) {
        try {
            List<ExamRecord> examRecords = examRecordService.getListByExamPublishRecordId(examDetailQueryFormDTO.getId());
            ExamPublishRecord examPublishRecord = examPublishRecordService.getById(examDetailQueryFormDTO.getId());
            if (examRecords != null && examRecords.size() != 0) {
                List<ExamReportRecordDetailTableDataDTO> tableDataDTOS = CommonUtils.convertList(examRecords, ExamReportRecordDetailTableDataDTO.class);
                for (ExamReportRecordDetailTableDataDTO tableDataDTO : tableDataDTOS) {
                    tableDataDTO.setTitle(examPublishRecord.getTitle());
                }
                return tableDataDTOS;
            }
        } catch (Exception e) {
            throw new ExamException(ExamError.QUERY_ERROR);
        }

        return null;
    }

    @Override
    public String analysisScore(List<ExamRecord> examRecords) {
        examRecords.forEach(examRecord -> {
            // 获取该场考试的答题情况
            List<AnswerRecord> answerRecords = answerRecordService.getListByExamRecordId(examRecord.getId());
            List<Long> subjectIds = answerRecords.stream().map(AnswerRecord::getPaperSubjectId).collect(Collectors.toList());
        });
        return null;
    }

    /**
     * 根据考试发布Id查询对应试卷满分
     *
     * @param examId
     * @return
     */
    @Override
    public Double findMaxScore(long examId) {
        ExamPublishRecord record = examPublishRecordService.getById(examId);
        if (record == null) {
            throw new ExamException(ExamError.EXAM_RECORD_NOT_EXIST);
        }
        long paperId = record.getPaperId();
        return RPCUtils.parseResponse(paperApi.queryPaperScore(new CommonRequest<>(commonState.getVersion(), TokenUtils.getToken(), paperId)), Double.class, RPCUtils.PAPER);
    }
}
