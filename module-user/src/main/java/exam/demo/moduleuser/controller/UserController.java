package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.dto.UserRoleDto;
import exam.demo.moduleuser.pojo.model.User;
import exam.demo.moduleuser.pojo.vo.RoleListVo;
import exam.demo.moduleuser.pojo.vo.UserItemVo;
import exam.demo.moduleuser.pojo.vo.UserListVo;
import exam.demo.moduleuser.pojo.vo.UserQueryVo;
import exam.demo.moduleuser.service.IPositionService;
import exam.demo.moduleuser.service.IRoleService;
import exam.demo.moduleuser.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Slf4j
@RestController
@RequestMapping("/usermanagement")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class UserController {
    @Autowired
    CacheManager cacheManager;

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IPositionService positionService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping("/save")
    public CommonResponse<Boolean> saveUser(@RequestBody @Valid UserItemVo request) {
        UserDto userDto = CommonUtils.copyProperties(request, UserDto.class);
        userService.save(userDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/update")
    public CommonResponse<Boolean> updateUser(@RequestBody @Valid UserItemVo request) {
        UserDto userDto = CommonUtils.copyProperties(request, UserDto.class);
        userDto.setOldVersion(userDto.getVersion());
        userService.update(userDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/form")
    public CommonResponse<UserListVo> getUpdateFormUser(@RequestBody @Valid Long request) {
        User user = userService.getById(request);
        UserListVo userListVo = CommonUtils.copyProperties(user, UserListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, userListVo);
    }

    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> deleteUser(@RequestBody @Valid List<UserItemVo> request) {
        List<UserDto> userDtoList = CommonUtils.convertList(request, UserDto.class);
        userService.delete(userDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<UserListVo>> queryUser(@RequestBody @Valid UserQueryVo request) {
        UserDto userDto = CommonUtils.copyProperties(request, UserDto.class);
        userDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<UserListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        List<User> userList = userService.queryByCondition(userDto);
        List<UserListVo> userListVoList = CommonUtils.convertList(userList, UserListVo.class);
        PageVo<UserListVo> pageVo = PageMapUtil.getPageMap(userListVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping("/options")
    public CommonResponse<Map> queryUserOptions() {
        List<UserOptionsDto> userRole = roleService.queryRole();
        List<UserOptionsDto> userPosition = positionService.queryPosition();
        Map<String, List> map = new HashMap<>(2);
        map.put("role", userRole);
        map.put("position", userPosition);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, map);
    }

    @MethodEnhancer
    @GetMapping("/list")
    public CommonResponse<PageVo<UserListVo>> getUserManagementList(@RequestBody UserQueryVo queryVo) {
        Long pageNum = (long) (queryVo.getCurrentPage() > 0 ? queryVo.getCurrentPage() : 1);
        Long pageSize = (long) (queryVo.getPageSize() > 0 ? queryVo.getPageSize() : 10);
        Page<UserListVo> page = new Page<>(pageNum, pageSize);
        UserDto userDto = CommonUtils.copyProperties(queryVo, UserDto.class);
        try {
            userDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        } catch (Exception e) {
            // 没有登录时，不设置 judgeId
        }
        List<User> userList = userService.queryByCondition(userDto);
        List<UserListVo> userListVoList = CommonUtils.convertList(userList, UserListVo.class);
        PageVo<UserListVo> pageVo = PageMapUtil.getPageMap(userListVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }


    @MethodEnhancer
    @PostMapping("/alloc")
    public CommonResponse<Boolean> allocRoleUser(@RequestBody @Valid UserRoleDto request) {
        userService.addRoleForUser(request);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    /**
     * 查询用户所拥有的角色
     *
     * @param commonRequest 用户Id
     * @return 是否查询成功，成功返回角色list，若失败返回失败信息
     */
    @MethodEnhancer
    @PostMapping("/role")
    public CommonResponse<List<RoleListVo>> queryRoleOfUser(@RequestBody Long commonRequest) {
        List<RoleDto> roleDtoList = userService.queryRoleOfUser(commonRequest);
        List<RoleListVo> roleListVoList = CommonUtils.convertList(roleDtoList, RoleListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, roleListVoList);
    }

    @MethodEnhancer
    @PostMapping("/exist")
    public CommonResponse<Boolean> existCode(@RequestBody String code) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, userService.notExistCode(code));
    }
}
