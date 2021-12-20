package exam.demo.moduleuser.api;


import exam.demo.modulecommon.common.*;
import exam.demo.moduleuser.constant.ApiConstant;
import exam.demo.moduleuser.service.ICompanyService;
import exam.demo.moduleuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@RestController
public class UserApiController {
    @Autowired
    IUserService userService;

    @Autowired
    CommonState state;

    @Autowired
    ICompanyService companyService;

    /**
     * 获取公司和用户名称
     *
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_USER_NAME)
    public CommonResponse<CompanyAndUserVo> getUserInfo(@RequestBody CommonRequest<List<Long>> request) {
        CompanyAndUserVo res = userService.getUserData(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, res);
    }

    /**
     * 通过用户id查询用户名
     *
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_USER_NAME_BY_ID)
    public CommonResponse<String> getUserNameById(@RequestBody CommonRequest<Long> request) {
        String name = userService.getUserName(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, name);
    }

    /**
     * 查询阅卷官
     *
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_SCORING_OFFICER)
    public CommonResponse<List<UserDto>> queryScoringOfficer(@RequestBody CommonRequest<UserDto> request) {
        List<UserDto> userDtoList = userService.queryScoringOfficerList(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, userDtoList);
    }

    /**
     * 由于是同一公司才能访问因此发生重名概率很低，而且只是通过名称获取id
     *
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_ID_BY_NAME)
    public CommonResponse<Long> getUserIdByName(@RequestBody CommonRequest<String> request) {
        Long mostPossibleId = userService.getMostPossibleUserId(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, mostPossibleId);
    }

    /**
     * 通过公司Id获取公司名称
     *
     * @param request
     * @return
     */
    @PostMapping(ApiConstant.GET_COMPANY_NAME)
    public CommonResponse<String> getCompanyById(@RequestBody CommonRequest<Long> request) {
        String company = companyService.getNameById(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, company);
    }
}
