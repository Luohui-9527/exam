package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.UserOnlineInfoMapper;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;
import exam.demo.moduleauth.service.IUserOnlineInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户在线记录表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoMapper, UserOnlineInfo> implements IUserOnlineInfoService {
    @Override
    public List<UserOnlineInfo> listOnlineUser() {
        return baseMapper.listOnlineUser();
    }
}
