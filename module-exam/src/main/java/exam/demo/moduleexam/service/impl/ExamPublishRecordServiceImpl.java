package exam.demo.moduleexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleexam.exception.ExamError;
import exam.demo.moduleexam.exception.ExamException;
import exam.demo.moduleexam.mapper.ExamPublishRecordMapper;
import exam.demo.moduleexam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.publish.*;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;
import exam.demo.moduleexam.pojo.model.UserRecord;
import exam.demo.moduleexam.service.ExamPublishRecordService;
import exam.demo.moduleexam.service.UserRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamPublishRecordServiceImpl extends ServiceImpl<ExamPublishRecordMapper, ExamPublishRecord> implements ExamPublishRecordService {

    @Resource
    private ExamPublishRecordMapper examPublishRecordMapper;

    @Resource
    private UserRecordService userRecordService;


    @Override
    public List<ExamPublishRecordTableDataDTO> queryExamPublishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO) {
        List<ExamPublishRecord> examPublishRecords = examPublishRecordMapper.queryExamPubulishRecord(examPublishRecordQueryFormDTO);
        if (examPublishRecords != null && examPublishRecords.size() != 0) {
            List<ExamPublishRecordTableDataDTO> examPublishRecordTableDataDTOS = CommonUtils.convertList(examPublishRecords, ExamPublishRecordTableDataDTO.class);
            examPublishRecordTableDataDTOS.forEach(dto -> {
                List<UserRecord> selected = userRecordService.listExaminers(dto.getId());
                List<Long> examiners = selected.stream().map(UserRecord::getId).collect(Collectors.toList());
                dto.setExaminers(examiners);
            });
            return examPublishRecordTableDataDTOS;
        }
        return new ArrayList<>();
    }


    @FullCommonField
    @Override
    public boolean addExamPublishRecord(ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO) {
        ExamPublishRecord examPublishRecord = new ExamPublishRecord();
        UserRecord userRecord = new UserRecord();
        UserPermission userPermission = TokenUtils.getUser();
        examPublishRecordPublishFormDTO.setPublisher(userPermission.getId());
        BeanUtils.copyProperties(examPublishRecordPublishFormDTO, examPublishRecord);
        examPublishRecord.setStatus((byte) 0);
        userRecord.setExamPublishRecordId(examPublishRecordPublishFormDTO.getId());
        List<Long> idList = examPublishRecordPublishFormDTO.getExaminersId();
        if (idList != null) {
            idList.forEach(id -> {
                userRecord.setId(id);
                userRecordService.save(userRecord);
            });
        }
        return examPublishRecordMapper.insert(examPublishRecord) == 1;
    }


    @Override
    public boolean deleteExamPublishRecord(List<ExamPublishRecordDeleteFormDTO> dtoList) {
        for (ExamPublishRecordDeleteFormDTO dto : dtoList) {
            List<ExamPublishRecord> recordList = this.getListByIdVersion(dto.getId(), dto.getVersion());
            if (recordList != null) {
                ExamPublishRecord examPublishRecord = recordList.get(0);
                if (examPublishRecord.getStatus() != 0) {
                    throw new ExamException(ExamError.PAPER_IS_PUBLISHED);
                }
                examPublishRecordMapper.deleteExamPublishRecord(dto);
            }
        }
        return true;
    }


    @Override
    public boolean updateExamPublishRecord(ExamPublishRecordEditFormDTO examPublishRecordEditFormDTO) {
        ExamPublishRecord examPublishRecord = new ExamPublishRecord();
        ExamPublishRecord oldPo = baseMapper.selectById(examPublishRecordEditFormDTO.getId());
        BeanUtils.copyProperties(oldPo, examPublishRecord);
        BeanUtils.copyProperties(examPublishRecordEditFormDTO, examPublishRecord);
        return examPublishRecordMapper.updateById(examPublishRecord) == 1;
    }

    @Override
    public List<ExamPublishRecord> getListByIdVersion(Long id, Long version) {
        QueryWrapper<ExamPublishRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.ID, id);
        wrapper.eq(MagicPointConstant.VERSION, version);
        List<ExamPublishRecord> recordList = this.list(wrapper);
        if (CollectionUtils.isEmpty(recordList)) {
            return null;
        } else {
            return recordList;
        }
    }

    @Override
    public List<ExamPublishRecord> queryGrade(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO) {
        return baseMapper.queryGrade(examGradeRecordQueryFormDTO);
    }

    @Override
    public List<ExamPublishRecord> getExamPublishRecordList(String title, Long examSession, Date startTime, Date endTime) {
        QueryWrapper<ExamPublishRecord> wrapper = new QueryWrapper<>();
        wrapper.like(MagicPointConstant.TITLE, title);
        wrapper.eq(MagicPointConstant.EXAM_SESSION, examSession);
        wrapper.between(MagicPointConstant.END_TIME, startTime, endTime);
        return this.list(wrapper);
    }
}

