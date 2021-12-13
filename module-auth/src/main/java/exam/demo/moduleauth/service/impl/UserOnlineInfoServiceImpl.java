package exam.demo.moduleauth.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.dao.UserOnlineInfoDao;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;
import exam.demo.moduleauth.service.UserOnlineInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoDao, UserOnlineInfo> implements UserOnlineInfoService {
}
