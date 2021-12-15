package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.UserOnlineInfoDto;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;

import java.util.List;

/**
 * 用户在线记录表 - 服务接口
 *
 * @author gpmscloud
 */
public interface IUserOnlineInfoService extends IService<UserOnlineInfo> {

    List<UserOnlineInfo> queryByCondition(UserOnlineInfo userOnlineInfo);

    /**
     * 根据请求条件查询符合条件的全部用户在线记录集合
     *
     * @param userOnlineInfoDTO 请求条件查询信息
     * @return
     */
    List<UserOnlineInfoDto> queryAllByCondition(UserOnlineInfoDto userOnlineInfoDTO);
}
