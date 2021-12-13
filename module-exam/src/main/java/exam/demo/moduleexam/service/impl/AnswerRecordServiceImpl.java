package exam.demo.moduleexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.moduleexam.dao.mapper.AnswerRecordMapper;
import exam.demo.moduleexam.pojo.DTO.dopaper.DoPaperFormDTO;
import exam.demo.moduleexam.pojo.model.AnswerRecord;
import exam.demo.moduleexam.service.AnswerRecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AnswerRecordServiceImpl extends ServiceImpl<AnswerRecordMapper, AnswerRecord> implements AnswerRecordService {


    @Override
    public AnswerRecord getOneByDoPaperFormDTO(DoPaperFormDTO doPaperFormDTO) {
        QueryWrapper<AnswerRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.MY_ANSWER, doPaperFormDTO.getMyAnswer());
        wrapper.eq(MagicPointConstant.PAPER_SUBJECT_ID, doPaperFormDTO.getPaperSubjectId());
        wrapper.eq(MagicPointConstant.EXAM_RECORD_ID, doPaperFormDTO.getExamRecordId());
        List<AnswerRecord> answerRecordList = this.list(wrapper);
        if (CollectionUtils.isEmpty(answerRecordList)) {
            return null;
        } else {
            return answerRecordList.get(0);
        }
    }

    @Override
    public AnswerRecord getOneByPaperSubjectIdAndExamRecordId(Long paperSubjectId, Long examRecordId) {
        QueryWrapper<AnswerRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.PAPER_SUBJECT_ID, paperSubjectId);
        wrapper.eq(MagicPointConstant.EXAM_RECORD_ID, examRecordId);
        List<AnswerRecord> answerRecordList = this.list(wrapper);
        if (CollectionUtils.isEmpty(answerRecordList)) {
            return null;
        } else {
            return answerRecordList.get(0);
        }
    }

    @Override
    public List<AnswerRecord> getListByExamRecordId(Long examRecordId) {
        QueryWrapper<AnswerRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.EXAM_RECORD_ID, examRecordId);
        return this.list(wrapper);
    }
}

