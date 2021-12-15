package exam.demo.moduleexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.moduleexam.dao.mapper.ExamRecordMapper;
import exam.demo.moduleexam.pojo.model.ExamRecord;
import exam.demo.moduleexam.service.ExamRecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ExamRecordServiceImpl extends ServiceImpl<ExamRecordMapper, ExamRecord> implements ExamRecordService {


    @Override
    public ExamRecord getOneByTel(String tel) {
        QueryWrapper<ExamRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.TEL, tel);
        List<ExamRecord> examRecords = this.list(wrapper);
        if (CollectionUtils.isEmpty(examRecords)) {
            return null;
        } else {
            return examRecords.get(0);
        }
    }

    @Override
    public List<ExamRecord> getListByExamPublishRecordId(Integer examPublishRecordId) {
        QueryWrapper<ExamRecord> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.EXAM_PUBLISH_RECORD_ID, examPublishRecordId);
        wrapper.orderByDesc(MagicPointConstant.SCORE);
        return this.list(wrapper);
    }
}

