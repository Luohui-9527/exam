package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.dto.UserRoleDto;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.model.User;
import exam.demo.moduleuser.pojo.vo.*;
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
@RequestMapping(ControllerConstants.USER)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
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
    @PostMapping(ControllerConstants.SAVE_U)
    public CommonResponse<Boolean> saveUser(@RequestBody @Valid CommonRequest<UserItemVo> request) {
        UserDto userDto = CommonUtils.copyProperties(request.getData(), UserDto.class);
        userService.save(userDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_U)
    public CommonResponse<Boolean> updateUser(@RequestBody @Valid CommonRequest<UserItemVo> request) {
        UserDto userDto = CommonUtils.copyProperties(request.getData(), UserDto.class);
        userDto.setOldVersion(userDto.getVersion());
        userService.update(userDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_U)
    public CommonResponse<UserListVo> getUpdateFormUser(@RequestBody @Valid CommonRequest<Long> request) {
        User user = userService.getById(request.getData());
        UserListVo userListVo = CommonUtils.copyProperties(user, UserListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, userListVo);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_U)
    public CommonResponse<Boolean> deleteUser(@RequestBody @Valid CommonRequest<List<UserItemVo>> request) {
        List<UserDto> userDtoList = CommonUtils.convertList(request.getData(), UserDto.class);
        userService.delete(userDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_U)
    public CommonResponse<PageVo<UserListVo>> queryUser(@RequestBody @Valid CommonRequest<UserQueryVo> request) {
        UserDto userDto = CommonUtils.copyProperties(request.getData(), UserDto.class);
        userDto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<UserListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<User> userList = userService.queryByCondition(userDto);
        List<UserListVo> userListVoList = CommonUtils.convertList(userList, UserListVo.class);
        PageVo<UserListVo> pageVo = PageMapUtil.getPageMap(userListVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_OPTIONS_U)
    public CommonResponse<Map> queryUserOptions() {
        List<UserOptionsDto> userRole = roleService.queryRole();
        List<UserOptionsDto> userPosition = positionService.queryPosition();
        Map<String, List> map = new HashMap<>(2);
        map.put("role", userRole);
        map.put("position", userPosition);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, map);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_LIST_U)
    public CommonResponse<List<TreeListVo>> queryUserTree() {
        List<TreeList> treeListDtoList = userService.getQueryListData(CommonUtils.judgeCompanyAndOrg());
        List<TreeListVo> treeListVos = CommonUtils.convertList(treeListDtoList, TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, treeListVos);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.ALLOC_USER)
    public CommonResponse<Boolean> allocRoleUser(@RequestBody @Valid CommonRequest<UserRoleDto> request) {
        userService.addRoleForUser(request.getData());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    /**
     * 查询用户所拥有的角色
     *
     * @param commonRequest 用户Id
     * @return 是否查询成功，成功返回角色list，若失败返回失败信息
     */
    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_USER_ROLE)
    public CommonResponse<List<RoleListVo>> queryRoleOfUser(@RequestBody CommonRequest<Long> commonRequest) {
        List<RoleDto> roleDtoList = userService.queryRoleOfUser(commonRequest.getData());
        List<RoleListVo> roleListVoList = CommonUtils.convertList(roleDtoList, RoleListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, roleListVoList);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.EXIST_CODE)
    public CommonResponse<Boolean> existCode(@RequestBody CommonRequest<String> code) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, userService.notExistCode(code.getData()));
    }
}
