package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;
import exam.demo.moduleauth.service.IUserOnlineInfoService;
import exam.demo.moduleauth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Date;

@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserOnlineInfoService userOnlineInfoService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String key = message.toString();
        log.info("用户" + key + "下线");
        long id = Long.parseLong(key);
        if (userService.getById(id) != null) {
            UpdateWrapper<UserOnlineInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("status", 1);
            updateWrapper.eq("user_id", id);
            updateWrapper.set("offline_time", new Date());
            userOnlineInfoService.update(updateWrapper);
        }
    }
}
