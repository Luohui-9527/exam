package exam.demo.moduleauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;

import java.util.List;

/**
 * 用户在线记录表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IUserOnlineInfoService extends IService<UserOnlineInfo> {
    List<UserOnlineInfo> listOnlineUser();
}
