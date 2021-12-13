package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import exam.demo.moduleauth.dao.UserDao;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;
import exam.demo.moduleauth.service.UserOnlineInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
//@Service
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    @Resource
    UserDao userDao;

    @Autowired
    UserOnlineInfoService userOnlineInfoService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String key = message.toString();
        log.info("用户" + key + "下线");
        long id;
        if (userDao.exist(id = Long.parseLong(key))){
            UpdateWrapper<UserOnlineInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status",1);
            updateWrapper.eq("user_id",id);
            updateWrapper.set("offline_time",new Date());
            userOnlineInfoService.update(updateWrapper);
        }
    }
}
