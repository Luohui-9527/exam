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
import exam.demo.moduleuser.dto.ResourceDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.pojo.model.Resource;
import exam.demo.moduleuser.pojo.vo.ResourceItemVo;
import exam.demo.moduleuser.pojo.vo.ResourceListVo;
import exam.demo.moduleuser.pojo.vo.ResourceQueryVo;
import exam.demo.moduleuser.service.IResourceService;
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
@RequestMapping(ControllerConstants.RESOURCE)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class ResourceController {
    @Autowired
    IResourceService resourceService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_R)
    public CommonResponse<Boolean> saveResource(@RequestBody @Valid CommonRequest<ResourceItemVo> request) {
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(), ResourceDto.class);
        resourceService.save(resourceDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_R)
    public CommonResponse<ResourceListVo> queryUpdateFormResource(@RequestBody CommonRequest<Integer> request) {
        Resource resource = resourceService.getById(request.getData());
        ResourceListVo resourceListVo = CommonUtils.copyProperties(resource, ResourceListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, resourceListVo);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_R)
    public CommonResponse<Boolean> deleteResource(@RequestBody CommonRequest<List<ResourceItemVo>> request) {
        List<Resource> resourceList = CommonUtils.convertList(request.getData(), Resource.class);
        resourceService.delete(resourceList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_R)
    public CommonResponse<Boolean> updateResource(@RequestBody CommonRequest<ResourceItemVo> request) {
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(), ResourceDto.class);
        resourceDto.setOldVersion(resourceDto.getVersion());
        resourceService.update(resourceDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_R)
    public CommonResponse<PageVo<ResourceListVo>> queryResource(@RequestBody CommonRequest<ResourceQueryVo> request) {
        ResourceDto resourceDto = CommonUtils.copyProperties(request.getData(), ResourceDto.class);
        Page<ResourceListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<Resource> resourceList = resourceService.list(resourceDto);
        List<ResourceListVo> listVos = CommonUtils.convertList(resourceList, ResourceListVo.class);
        PageVo<ResourceListVo> pageVo = PageMapUtil.getPageMap(listVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.QUERY_LIST_R)
    public CommonResponse<List<TreeListDto>> getResourceListResource() {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, resourceService.getQueryList());
    }
}
