package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.biz.service.OrganizationService;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.OrganizationDto;
import exam.demo.moduleuser.pojo.model.Organization;
import exam.demo.moduleuser.pojo.vo.OrganizationItemVo;
import exam.demo.moduleuser.pojo.vo.OrganizationListVo;
import exam.demo.moduleuser.pojo.vo.OrganizationQueryVo;
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
@RequestMapping(ControllerConstants.ORG)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_O)
    public CommonResponse<Boolean> saveOrganization(@RequestBody @Valid CommonRequest<OrganizationItemVo> request) {
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(), OrganizationDto.class);
        organizationService.save(organizationDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_O)
    public CommonResponse<Boolean> deleteOrganization(@RequestBody @Valid CommonRequest<List<OrganizationItemVo>> request) {
        List<OrganizationDto> dtoList = CommonUtils.convertList(request.getData(), OrganizationDto.class);
        organizationService.delete(dtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_O)
    public CommonResponse<Boolean> updateOrganization(@RequestBody @Valid CommonRequest<OrganizationItemVo> request) {
        OrganizationDto organizationDto = CommonUtils.copyProperties(request.getData(), OrganizationDto.class);
        organizationDto.setOldVersion(organizationDto.getVersion());
        organizationService.update(organizationDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_O)
    public CommonResponse<PageVo<OrganizationListVo>> queryOrganization(@RequestBody CommonRequest<OrganizationQueryVo> request) {
        OrganizationDto dto = CommonUtils.copyProperties(request.getData(), OrganizationDto.class);
        Page<OrganizationListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<Organization> organizationList = organizationService.list(dto);
        List<OrganizationListVo> listVos = CommonUtils.convertList(organizationList, OrganizationListVo.class);
        PageVo<OrganizationListVo> pageVo = PageMapUtil.getPageMap(listVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF)
    public CommonResponse<OrganizationListVo> getUpdateFormOrganization(@RequestBody CommonRequest<Long> request) {
        Organization organization = organizationService.getById(request.getData());
        OrganizationListVo organizationListVo = CommonUtils.copyProperties(organization, OrganizationListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, organizationListVo);
    }
}
