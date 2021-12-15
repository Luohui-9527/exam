package exam.demo.moduleexam.service.impl;


import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.moduleexam.exception.ExamError;
import exam.demo.moduleexam.exception.ExamException;
import exam.demo.moduleexam.pojo.DTO.dopaper.DoPaperFormDTO;
import exam.demo.moduleexam.pojo.DTO.dopaper.UserInfoFormDTO;
import exam.demo.moduleexam.pojo.VO.dopaper.TimeWrapper;
import exam.demo.moduleexam.pojo.model.AnswerRecord;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import exam.demo.moduleexam.pojo.model.ExamRecord;
import exam.demo.moduleexam.pojo.model.UserRecord;
import exam.demo.moduleexam.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DoPaperServiceImpl implements DoPaperService {
    @Autowired
    private ExamRecordService examRecordService;
    @Autowired
    private ExamPublishRecordService examPublishRecordService;
    @Autowired
    private UserRecordService userRecordService;
    @Autowired
    private AnswerRecordService answerRecordService;

    @Autowired
    SnowFlake snowFlake;

    private Integer count = 0;

    @Override
    public Integer saveExaminee(UserInfoFormDTO userInfoFormDTO) {
        ExamRecord record = examRecordService.getOneByTel(userInfoFormDTO.getTel());
        if (record == null) {
            try {
                ExamRecord examRecord = new ExamRecord();
                examRecord.setId(snowFlake.nextId());
                examRecord.setActualStartTime(new Date());
                examRecord.setStatus((byte) 0);
                BeanUtils.copyProperties(userInfoFormDTO, examRecord);
                //id从dto拿 找到publish的开始结束时间
                ExamPublishRecord examPublishRecord = examPublishRecordService.getById(examRecord.getExamPublishRecordId());
                examPublishRecord.setStatus((byte) 1);
                examPublishRecordService.updateById(examPublishRecord);
                // 添加阅卷官
                List<UserRecord> userRecords = userRecordService.listExaminers(examPublishRecord.getId());
                examRecord.setUserId(userRecords.get(count++ % userRecords.size()).getId());
                examRecord.setPlanStartTime(examPublishRecord.getStartTime());
                examRecord.setPlanEndTime(examPublishRecord.getEndTime());
                if (examRecordService.save(examRecord)) {
                    return examRecord.getId();
                }
            } catch (Exception e) {
                throw new ExamException(ExamError.SAVE_USER_ERROR);
            }
        } else {
            return record.getId();
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addMyAnswer(List<DoPaperFormDTO> doPaperFormDTOList) {
        ExamRecord examRecord = examRecordService.getById(doPaperFormDTOList.get(0).getExamRecordId());
        examRecord.setActualEndTime(new Date());
        examRecordService.updateById(examRecord);
        Boolean flag = false;
//        查询是否存在答案记录
        for (DoPaperFormDTO doPaperFormDTO : doPaperFormDTOList) {
            AnswerRecord answerRecord = answerRecordService.getOneByDoPaperFormDTO(doPaperFormDTO);
            if (answerRecord == null) {
                AnswerRecord newAnswer = new AnswerRecord();
                newAnswer.setId(snowFlake.nextId());
                BeanUtils.copyProperties(doPaperFormDTO, newAnswer);
                // 判断是否为客观题
                if (answerRecordService.save(newAnswer)) {
                    flag = true;
                } else {
                    return false;
                }
            }
        }
        return flag;
    }

    /**
     * 获取考试持续时间
     *
     * @param id
     * @return
     */
    @Override
    public TimeWrapper getExamTime(long id) {
        ExamPublishRecord examPublishRecord = examPublishRecordService.getById(id);
        int time = examPublishRecord.getLimitTime();
        TimeWrapper wrapper = new TimeWrapper();
        wrapper.setHour(time / 60);
        wrapper.setMin(time % 60);
        return wrapper;
    }

}
