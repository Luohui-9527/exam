package exam.demo.moduleexam.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleexam.pojo.model.UserRecord;

import java.util.List;


public interface UserRecordMapper extends BaseMapper<UserRecord> {


    List<UserRecord> listExaminers(Long examPublishRecordId);
}