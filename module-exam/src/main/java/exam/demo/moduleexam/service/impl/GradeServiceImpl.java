package exam.demo.moduleexam.service.impl;

import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleexam.exception.ExamError;
import exam.demo.moduleexam.exception.ExamException;
import exam.demo.moduleexam.pojo.DTO.grade.*;
import exam.demo.moduleexam.pojo.model.AnswerRecord;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import exam.demo.moduleexam.pojo.model.ExamRecord;
import exam.demo.moduleexam.service.AnswerRecordService;
import exam.demo.moduleexam.service.ExamPublishRecordService;
import exam.demo.moduleexam.service.ExamRecordService;
import exam.demo.moduleexam.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private ExamRecordService examRecordService;
    @Autowired
    private ExamPublishRecordService examPublishRecordService;
    @Autowired
    private AnswerRecordService answerRecordService;

    @Override
    public List<ExamGradeRecordTableDataDTO> query(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordService.queryGrade(examGradeRecordQueryFormDTO);
        return convert(examPublishRecords);
    }


    @FullCommonField
    @Override
    public Boolean markingPaper(MarkingPaperDTO markingPaperDTO) {
        ExamRecord examRecord = examRecordService.getById(markingPaperDTO.getExamRecordId());
        BeanUtils.copyProperties(markingPaperDTO, examRecord);
        examRecord.setId(markingPaperDTO.getExamRecordId());
        examRecord.setActualEndTime(new Date());
        examRecord.setStatus((byte) 1);
        Map<String, Object> resultMap = markingAnswer(markingPaperDTO);
        if (resultMap.get("success").equals(true)) {
            examRecord.setObjectiveSubjectScore((Double) resultMap.get("objectiveSubjectScore"));
            examRecord.setSubjectvieSubjectScore((Double) resultMap.get("subjectiveSubjectScore"));
            return examRecordService.updateById(examRecord);
        }
        return false;
    }


    @Override
    public List<MyAnswerDTO> getMyAnswer(Integer examRecordId) {
        // 获取答题明细
        List<AnswerRecord> answerRecords = answerRecordService.getListByExamRecordId(examRecordId);
        if (answerRecords.size() == 0) {
            // 将对应的批改状态改为0分
            ExamRecord examRecord = examRecordService.getById(examRecordId);
            if (examRecord == null) {
                throw new ExamException(ExamError.EXAM_RECORD_NOT_EXIST);
            }
            examRecord.setObjectiveSubjectScore(0.0);
            examRecord.setSubjectvieSubjectScore(0.0);
            examRecord.setScore(0.0);
            examRecord.setSystemEvaluate("未作答，自动评分");
            examRecord.setUpdatedBy(TokenUtils.getUser().getId());
            examRecord.setUpdatedTime(new Date());
            examRecord.setStatus((byte) 1);
            examRecordService.updateById(examRecord);
            throw new ExamException(ExamError.DATA_NOT_EXIST);
        }
        return CommonUtils.convertList(answerRecords, MyAnswerDTO.class);
    }

    /**
     * 给每道题添加评分
     *
     * @param markingPaperDTO
     * @return true or false
     */
    private Map<String, Object> markingAnswer(MarkingPaperDTO markingPaperDTO) {
        Map<String, Object> result = new HashMap<>();
        Boolean flag = false;
        Double objectiveSubjectScore = 0.0;
        Double subjectiveSubjectScore = 0.0;
        for (MarkingAnswerDTO markingAnswerDTO : markingPaperDTO.getMarkingAnswerDTOList()) {
            AnswerRecord answerRecord = answerRecordService.getOneByPaperSubjectIdAndExamRecordId(markingAnswerDTO.getPaperSubjectId(), markingPaperDTO.getExamRecordId());
            if (answerRecord != null) {
                answerRecord.setScore(markingAnswerDTO.getScore());
                answerRecord.setEvaluate(markingAnswerDTO.getEvaluate());
                // 如果是客观题
                if (markingAnswerDTO.getObjectiveSubject()) {
                    objectiveSubjectScore += answerRecord.getScore();
                } else {
                    subjectiveSubjectScore += answerRecord.getScore();
                }
                if (answerRecordService.updateById(answerRecord)) {
                    flag = true;
                } else {
                    return null;
                }
            }
        }
        result.put("objectiveSubjectScore", objectiveSubjectScore);
        result.put("subjectiveSubjectScore", subjectiveSubjectScore);
        result.put("success", flag);
        return result;
    }

    private List<ExamGradeRecordTableDataDTO> convert(List<ExamPublishRecord> examPublishRecords) {
        List<ExamGradeRecordTableDataDTO> tableDataDTOS = new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamGradeRecordTableDataDTO tableDataDTO = new ExamGradeRecordTableDataDTO();
            for (ExamRecord examRecord : examPublishRecord.getExamRecords()) {
                BeanUtils.copyProperties(examRecord, tableDataDTO);
            }
            BeanUtils.copyProperties(examPublishRecord, tableDataDTO);
            tableDataDTOS.add(tableDataDTO);
        }
        return tableDataDTOS;
    }
}


