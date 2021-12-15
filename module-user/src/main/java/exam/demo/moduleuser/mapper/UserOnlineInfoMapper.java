package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;

import java.util.List;

/**
 * 用户在线记录表 - 数据访问接口
 *
 * @author gpmscloud
 */
public interface UserOnlineInfoMapper extends BaseMapper<UserOnlineInfo> {

    List<UserOnlineInfo> query(UserOnlineInfo userOnlineInfo);
}
