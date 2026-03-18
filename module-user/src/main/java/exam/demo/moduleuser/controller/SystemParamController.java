package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
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
@RequestMapping("/systemparam")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class SystemParamController {
    @Autowired
    ISystemParamService systemParamService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping("/save")
    public CommonResponse<Boolean> saveSystemParam(@RequestBody @Valid SystemParamItemVo request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request, SystemParamDto.class);
        systemParamService.save(systemParamDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/update")
    public CommonResponse<Boolean> updateSystemParam(@RequestBody @Valid SystemParamItemVo request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request, SystemParamDto.class);
        systemParamDto.setOldVersion(systemParamDto.getVersion());
        systemParamService.update(systemParamDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> deleteSystemParam(@RequestBody @Valid List<SystemParamItemVo> request) {
        List<SystemParamDto> systemParamDtoList = CommonUtils.convertList(request, SystemParamDto.class);
        systemParamService.delete(systemParamDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<SystemParamListVo>> querySystemParam(@RequestBody @Valid SystemParamQueryVo request) {
        SystemParamDto systemParamDto = CommonUtils.copyProperties(request, SystemParamDto.class);
        Page<SystemParamListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        List<SystemParamListVo> systemParamList = systemParamService.queryByCondition(systemParamDto);
        PageVo<SystemParamListVo> pageVo = PageMapUtil.getPageMap(systemParamList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping("/form")
    public CommonResponse<SystemParamListVo> getUpdateFormSystemParam(@RequestBody @Valid Long request) {
        SystemParam systemParam = systemParamService.getById(request);
        SystemParamListVo systemParamListVo = CommonUtils.copyProperties(systemParam, SystemParamListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, systemParamListVo);
    }


    @MethodEnhancer
    @GetMapping("/list")
    public CommonResponse<List> getTreeSystemParam() {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, systemParamService.getQueryListData());
    }
}
