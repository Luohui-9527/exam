package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.utils.AdminUtil;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleuser.dto.UserOnlineInfoDto;
import exam.demo.moduleuser.mapper.UserOnlineInfoMapper;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;
import exam.demo.moduleuser.service.IUserOnlineInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户在线记录表 - 服务实现
 *
 * @author gpmscloud
 */
@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoMapper, UserOnlineInfo> implements IUserOnlineInfoService {

    @Override
    public List<UserOnlineInfo> queryByCondition(UserOnlineInfo userOnlineInfo) {
        if (AdminUtil.isSuperAdmin()) {
            return list();
        }
        if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())) {
            userOnlineInfo.setJudgeId(null);
        } else {
            userOnlineInfo.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        return baseMapper.query(userOnlineInfo);
    }

    /**
     * 根据请求条件查询符合条件的全部用户在线记录集合
     *
     * @param userOnlineInfoDTO 请求条件查询信息
     * @return
     */
    @Override
    public List<UserOnlineInfoDto> queryAllByCondition(UserOnlineInfoDto userOnlineInfoDTO) {
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(userOnlineInfoDTO, UserOnlineInfo.class);
        if (CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())) {
            userOnlineInfo.setJudgeId(null);
        }
        List<UserOnlineInfo> userOnlineInfoList = baseMapper.query(userOnlineInfo);
        return CommonUtils.convertList(userOnlineInfoList, UserOnlineInfoDto.class);
    }
}
