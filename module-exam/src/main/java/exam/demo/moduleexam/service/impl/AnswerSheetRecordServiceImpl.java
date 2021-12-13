package exam.demo.moduleexam.service.impl;


import exam.demo.moduleexam.dao.mapper.ExamPublishRecordMapper;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.answersheet.ExamAnswerSheetRecordTableDataDTO;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import exam.demo.moduleexam.pojo.model.ExamRecord;
import exam.demo.moduleexam.service.AnswerSheetRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerSheetRecordServiceImpl implements AnswerSheetRecordService {

    @Resource
    ExamPublishRecordMapper examPublishRecordMapper;


    @Override
    public List<ExamAnswerSheetRecordTableDataDTO> queryAnswerSheet(ExamAnswerSheetRecordQueryFormDTO examAnswerSheetRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryAnswerRecord(examAnswerSheetRecordQueryFormDTO);
        return convert(examPublishRecords);
    }

    private List<ExamAnswerSheetRecordTableDataDTO> convert(List<ExamPublishRecord> examPublishRecords) {
        List<ExamAnswerSheetRecordTableDataDTO> examAnswerSheetRecordTableDataDTOS = new ArrayList<>();
        for (ExamPublishRecord examPublishRecord : examPublishRecords) {
            ExamAnswerSheetRecordTableDataDTO examAnswerSheetRecordTableDataDTO = new ExamAnswerSheetRecordTableDataDTO();
            for (ExamRecord examRecord : examPublishRecord.getExamRecords()) {
                BeanUtils.copyProperties(examRecord, examAnswerSheetRecordTableDataDTO);
            }
            BeanUtils.copyProperties(examPublishRecord, examAnswerSheetRecordTableDataDTO);
            examAnswerSheetRecordTableDataDTOS.add(examAnswerSheetRecordTableDataDTO);
        }
        return examAnswerSheetRecordTableDataDTOS;
    }
}
