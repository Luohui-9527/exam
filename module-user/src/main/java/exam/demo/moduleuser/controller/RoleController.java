package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.collection.CollUtil;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.dto.RoleDto;
import exam.demo.moduleuser.dto.RoleResourceDto;
import exam.demo.moduleuser.dto.RoleUserDto;
import exam.demo.moduleuser.pojo.model.Resource;
import exam.demo.moduleuser.pojo.model.Role;
import exam.demo.moduleuser.pojo.model.UserForRole;
import exam.demo.moduleuser.pojo.vo.*;
import exam.demo.moduleuser.service.ICompanyService;
import exam.demo.moduleuser.service.IResourceService;
import exam.demo.moduleuser.service.IRoleService;
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
@RequestMapping("/role")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class RoleController {
    @Autowired
    IRoleService roleService;

    @Autowired
    ICompanyService companyService;

    @Autowired
    IResourceService resourceService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping("/save")
    public CommonResponse<Boolean> saveRole(@RequestBody RoleItemVo request) {
        RoleDto roleDto = CommonUtils.copyProperties(request, RoleDto.class);
        roleService.save(roleDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/update")
    public CommonResponse<Boolean> updateRole(@RequestBody RoleItemVo request) {
        RoleDto roleDto = CommonUtils.copyProperties(request, RoleDto.class);
        roleDto.setOldVersion(request.getVersion());
        roleService.update(roleDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/form")
    public CommonResponse<RoleListVo> getUpdateFormRole(@RequestBody Long request) {
        Role role = roleService.getById(request);
        RoleListVo listVo = CommonUtils.copyProperties(role, RoleListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, listVo);
    }

    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> deleteRole(@RequestBody List<RoleItemVo> request) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (RoleItemVo datum : request) {
            roleDtoList.add(new RoleDto(datum.getId()));
        }
        roleService.delete(roleDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<RoleListVo>> queryRole(@RequestBody RoleQueryVo request) {
        Page<RoleListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        Role role = CommonUtils.copyProperties(request, Role.class);
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
    @PostMapping("/allocUser")
    public CommonResponse<Boolean> allocUserRole(@RequestBody List<RoleUserVo> request) {
        List<RoleUserDto> userDtoList = CommonUtils.convertList(request, RoleUserDto.class);
        roleService.addUserForRole(userDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/allocResource")
    public CommonResponse<Boolean> updateResourceForRole(@RequestBody List<RoleResourceVo> request) {
        List<Long> resourceIdList = request.stream().map(RoleResourceVo::getId).collect(Collectors.toList());
        Set<RoleResourceVo> set = new HashSet<>(request);
        if (!CollUtil.isEmpty(set)) {
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
    @PostMapping("/userForm")
    public CommonResponse<PageVo<UserForRoleListVo>> getUserRoleForm(@RequestBody UserAlloctionQueryVo request) {
        Role role = CommonUtils.copyProperties(request, Role.class);
        role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<RoleListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        List<UserForRole> roleList = roleService.queryUserRole(role);
        List<UserForRoleListVo> userForRoleListVoList = CommonUtils.convertList(roleList, UserForRoleListVo.class);
        PageVo<UserForRoleListVo> pageVo = PageMapUtil.getPageMap(userForRoleListVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping("/resourceForm")
    public CommonResponse<List> getResourceForRoleForm(@RequestBody RoleItemVo request) {
        RoleDto roleDto = CommonUtils.copyProperties(request, RoleDto.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, roleService.queryResourceForRole(roleDto));
    }


    @MethodEnhancer
    @PostMapping("/list")
    public CommonResponse<PageVo<RoleListVo>> getRoleList(@RequestBody RoleQueryVo queryVo) {
        Long pageNum = (long) (queryVo.getCurrentPage() > 0 ? queryVo.getCurrentPage() : 1);
        Long pageSize = (long) (queryVo.getPageSize() > 0 ? queryVo.getPageSize() : 10);
        Page<RoleListVo> page = new Page<>(pageNum, pageSize);
        Role role = CommonUtils.copyProperties(queryVo, Role.class);
        try {
            role.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        } catch (Exception e) {
            // 没有登录时，不设置 judgeId
        }
        List<Role> roleList = roleService.queryByCondition(role);
        List<RoleListVo> roleListVos = CommonUtils.convertList(roleList, RoleListVo.class);
        PageVo<RoleListVo> pageVo = PageMapUtil.getPageMap(roleListVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }
}
