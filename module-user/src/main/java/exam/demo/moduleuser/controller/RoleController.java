package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.biz.service.CompanyService;
import exam.demo.moduleuser.biz.service.ResourceService;
import exam.demo.moduleuser.biz.service.RoleService;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.RoleResourceDto;
import exam.demo.moduleuser.dto.RoleUserDto;
import exam.demo.moduleuser.pojo.model.Resource;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.UserForRole;
import exam.demo.moduleuser.pojo.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.ROLE)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    CompanyService companyService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_ROLE)
    public CommonResponse<Boolean> saveRole(@RequestBody CommonRequest<RoleItemVo> request) {
        RoleDto roleDto = CommonUtils.copyProperties(request.getData(), RoleDto.class);
        roleService.save(roleDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_ROLE)
    public CommonResponse<Boolean> updateRole(@RequestBody CommonRequest<RoleItemVo> request) {
        RoleDto roleDto = CommonUtils.copyProperties(request.getData(), RoleDto.class);
        roleService.update(roleDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_ROLE)
    public CommonResponse<RoleListVo> getUpdateFormRole(@RequestBody CommonRequest<Long> request) {
        Role role = roleService.getById(request.getData());
        RoleListVo listVo = CommonUtils.copyProperties(role, RoleListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, listVo);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_ROLE)
    public CommonResponse<Boolean> deleteRole(@RequestBody CommonRequest<List<RoleItemVo>> request) {
        List<RoleItemVo> data = request.getData();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleItemVo datum : data) {
            roleDtoList.add(RoleDto.builder().id(datum.getId()).build());
        }
        roleService.delete(roleDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_ROLE)
    public CommonResponse<PageVo<RoleListVo>> queryRole(@RequestBody CommonRequest<RoleQueryVo> request) {
        Page<RoleListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        Role role = CommonUtils.copyProperties(request.getData(), Role.class);
        List<Role> roleList = roleService.queryByCondition(role);
        List<RoleListVo> roleListVos = CommonUtils.convertList(roleList, RoleListVo.class);
        PageVo<RoleListVo> pageVo = PageMapUtil.getPageMap(roleListVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    /**
     * 为用户分配角色
     *
     * @param request
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstants.ALLOC_USER_FOR_ROLE)
    public CommonResponse<Boolean> allocUserRole(@RequestBody CommonRequest<List<RoleUserVo>> request) {
        List<RoleUserDto> userDtoList = CommonUtils.convertList(request.getData(), RoleUserDto.class);
        roleService.addUserForRole(userDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.UPDATE_RESOURCE_FOR_ROLE)
    public CommonResponse<Boolean> updateResourceForRole(@RequestBody CommonRequest<List<RoleResourceVo>> request) {
        List<Long> resourceIdList = request.getData().stream().map(RoleResourceVo::getId).collect(Collectors.toList());
        List<RoleResourceVo> voList = request.getData();
        // 前端的代码有bug,存储的数据是不断的增加的，因此需要去重
        Set<RoleResourceVo> set = new HashSet<>(voList);
        if (!CommonUtils.isEmpty(set)) {
            List<Resource> resourceList = resourceService.listByIdList(resourceIdList);
            for (RoleResourceVo vo : set) {
                for (Resource resource : resourceList) {
                    if (vo.getResourceId().equals(resource.getId())) {
                        vo.setType(resource.getResourceType());
                        break;
                    }
                }
            }
        }
        List<RoleResourceDto> dtoList = CommonUtils.convertList(set, RoleResourceDto.class);
        roleService.addResourceForRole(dtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_USER_FOR_ROLE_FORM)
    public CommonResponse<PageVo<UserForRoleListVo>> getUserRoleForm(@RequestBody CommonRequest<UserAlloctionQueryVo> request) {
        Role role = CommonUtils.copyProperties(request.getData(), Role.class);
        role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<RoleListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<UserForRole> roleList = roleService.queryUserRole(role);
        List<UserForRoleListVo> userForRoleListVoList = CommonUtils.convertList(roleList, UserForRoleListVo.class);
        PageVo<UserForRoleListVo> pageVo = PageMapUtil.getPageMap(userForRoleListVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_RESOURCE_FOR_ROLE_FORM)
    public CommonResponse<List> getResourceForRoleForm(@RequestBody CommonRequest<RoleItemVo> request) {
        RoleDto roleDto = CommonUtils.copyProperties(request.getData(), RoleDto.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, roleService.queryResourceForRole(roleDto));
    }


    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_LIST_ROLE)
    public CommonResponse<List> getQueryListDataRole() {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, companyService.getCompanyTree(CommonUtils.judgeCompanyAndOrg()));
    }
}
