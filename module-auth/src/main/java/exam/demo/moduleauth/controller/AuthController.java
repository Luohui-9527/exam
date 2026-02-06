package exam.demo.moduleauth.controller;

import exam.demo.moduleauth.exception.AuthError;
import exam.demo.moduleauth.exception.AuthException;
import exam.demo.moduleauth.pojo.dto.UserDto;
import exam.demo.moduleauth.pojo.model.UserInfo;
import exam.demo.moduleauth.pojo.vo.UserVo;
import exam.demo.moduleauth.service.LoginService;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/26
 */
@RestController
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class AuthController {
    @Autowired
    private LoginService loginService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(value = "/login")
    public CommonResponse<Map> check(@RequestBody @Valid UserVo userVO) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getCode(), userVO.getPassword());
        try {
            subject.login(token);
        } catch (Exception e) {
            throw new AuthException(AuthError.LOGIN_FAIL);
        }
        UserDto userDto = CommonUtils.copyProperties(userVO, UserDto.class);
        Map<String, Object> data = loginService.createToken(userDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, data);

    }

    @MethodEnhancer
    @PostMapping(value = "/getInfo")
    public CommonResponse<UserInfo> getInfo(@RequestBody CommonRequest request) {
        String token = request.getToken();
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, loginService.getUserInfo(token));
    }

    @MethodEnhancer
    @PostMapping(value = "/getMenu")
    public CommonResponse<List> getMenu(@RequestBody CommonRequest request) {
        String token = request.getToken();
        List list = loginService.getUserMenu(token);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, list);
    }

    @MethodEnhancer
    @RequestMapping(value = "/logout")
    public CommonResponse<Boolean> logout(@RequestBody CommonRequest<List<Long>> commonRequest) throws Exception {
        List<Long> ids = commonRequest.getData();
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, loginService.logout(ids));
    }

}
