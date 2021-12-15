package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.SystemParamDto;
import exam.demo.moduleuser.pojo.model.SystemParam;
import exam.demo.moduleuser.pojo.vo.SystemParamItemVo;
import exam.demo.moduleuser.pojo.vo.SystemParamListVo;
import exam.demo.moduleuser.pojo.vo.SystemParamQueryVo;
import exam.demo.moduleuser.service.ISystemParamService;
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
@RequestMapping(ControllerConstants.SYSTEM_PARAM)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class SystemParamController {
    @Autowired
    ISystemParamService systemParamService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_SP)
    public CommonResponse<Boolean> saveSystemParam(@RequestBody @Valid CommonRequest<SystemParamItemVo> request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(), SystemParamDto.class);
        systemParamService.save(systemParamDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_SP)
    public CommonResponse<Boolean> updateSystemParam(@RequestBody @Valid CommonRequest<SystemParamItemVo> request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(), SystemParamDto.class);
        systemParamDto.setOldVersion(systemParamDto.getVersion());
        systemParamService.update(systemParamDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_SP)
    public CommonResponse<Boolean> deleteSystemParam(@RequestBody @Valid CommonRequest<List<SystemParamItemVo>> request) {
        List<SystemParamDto> systemParamDtoList = CommonUtils.convertList(request.getData(), SystemParamDto.class);
        systemParamService.delete(systemParamDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_SP)
    public CommonResponse<PageVo<SystemParamListVo>> querySystemParam(@RequestBody @Valid CommonRequest<SystemParamQueryVo> request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request.getData(), SystemParamDto.class);
        Page<SystemParamListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<SystemParamListVo> systemParamList = systemParamService.queryByCondition(systemParamDto);
        PageVo<SystemParamListVo> pageVo = PageMapUtil.getPageMap(systemParamList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_SP)
    public CommonResponse<SystemParamListVo> getUpdateFormSystemParam(@RequestBody @Valid CommonRequest<Integer> request) {
        SystemParam systemParam = systemParamService.getById(request.getData());
        SystemParamListVo systemParamListVo = CommonUtils.copyProperties(systemParam, SystemParamListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, systemParamListVo);
    }


    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_LIST_SP)
    public CommonResponse<List> getTreeSystemParam() {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, systemParamService.getQueryListData());
    }
}
