package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.utils.AdminUtil;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleuser.biz.dao.UserOnlineInfoDao;
import exam.demo.moduleuser.biz.service.UserOnlineInfoService;
import exam.demo.moduleuser.dto.UserOnlineInfoDto;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class UserOnlineInfoServiceImpl extends ServiceImpl<UserOnlineInfoDao, UserOnlineInfo> implements UserOnlineInfoService {
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
