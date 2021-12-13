package exam.demo.moduleexam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleexam.dao.mapper.UserRecordMapper;
import exam.demo.moduleexam.pojo.model.UserRecord;
import exam.demo.moduleexam.service.UserRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRecordServiceImpl extends ServiceImpl<UserRecordMapper, UserRecord> implements UserRecordService {


    @Override
    public List<UserRecord> listExaminers(Long examPublishRecordId) {
        return baseMapper.listExaminers(examPublishRecordId);
    }
}

