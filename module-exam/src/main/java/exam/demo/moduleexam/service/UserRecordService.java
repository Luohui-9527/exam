package exam.demo.moduleexam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleexam.pojo.model.UserRecord;

import java.util.List;


public interface UserRecordService extends IService<UserRecord> {

    List<UserRecord> listExaminers(Integer examPublishRecordId);
}
