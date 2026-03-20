package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.dto.UserOnlineInfoDto;
import exam.demo.moduleuser.pojo.model.UserOnlineInfo;
import exam.demo.moduleuser.pojo.vo.UserOnlineInfoListVo;
import exam.demo.moduleuser.pojo.vo.UserOnlineInfoQueryVo;
import exam.demo.moduleuser.service.IUserOnlineInfoService;
import exam.demo.moduleuser.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/useronline")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class UserOnlineController {
    @Autowired
    IUserOnlineInfoService userOnlineInfoService;

    @Autowired
    LoginService loginService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<UserOnlineInfo>> queryUserOnline(@RequestBody @Valid UserOnlineInfoQueryVo request) {
        UserOnlineInfo userOnlineInfo = CommonUtils.copyProperties(request, UserOnlineInfo.class);
        List<UserOnlineInfo> onlineInfoList = userOnlineInfoService.queryByCondition(userOnlineInfo);
        Page<UserOnlineInfoListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        PageVo<UserOnlineInfo> pageVo = PageMapUtil.getPageMap(onlineInfoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }


    @MethodEnhancer
    @PostMapping("/all")
    public CommonResponse<List> queryAllUserOnline(@RequestBody UserOnlineInfoQueryVo request) {
        UserOnlineInfoDto userOnlineInfoDto = CommonUtils.copyProperties(request, UserOnlineInfoDto.class);
        userOnlineInfoDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        List<UserOnlineInfoDto> userOnlineInfoDtoList = userOnlineInfoService.queryAllByCondition(userOnlineInfoDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, CommonUtils.convertList(userOnlineInfoDtoList, UserOnlineInfoListVo.class));
    }


    @MethodEnhancer
    @PostMapping("/offline")
    public CommonResponse<Boolean> kick(@RequestBody List<String> request) {
        loginService.logout(request);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }
}
