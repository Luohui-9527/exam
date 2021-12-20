package exam.demo.moduleauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleauth.pojo.model.UserOnlineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户在线记录表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface UserOnlineInfoMapper extends BaseMapper<UserOnlineInfo> {


    List<UserOnlineInfo> listOnlineUser();


    boolean updateOnlineState(@Param("ids") List<Integer> ids);

}
