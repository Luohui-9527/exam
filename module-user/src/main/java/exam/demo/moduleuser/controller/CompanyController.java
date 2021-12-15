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
import exam.demo.moduleuser.dto.CompanyDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.Company;
import exam.demo.moduleuser.pojo.vo.CompanyItemVo;
import exam.demo.moduleuser.pojo.vo.CompanyListVo;
import exam.demo.moduleuser.pojo.vo.CompanyQueryVo;
import exam.demo.moduleuser.pojo.vo.TreeListVo;
import exam.demo.moduleuser.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.COMPANY)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class CompanyController {
    @Autowired
    ICompanyService companyService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_C)
    public CommonResponse<Boolean> saveCompany(@RequestBody @Valid CommonRequest<CompanyItemVo> request) {
        CompanyDto dto = CommonUtils.copyProperties(request.getData(), CompanyDto.class);
        companyService.save(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DELETE_C)
    public CommonResponse<Boolean> deleteCompany(@RequestBody @Valid CommonRequest<List<CompanyItemVo>> request) {
        List<CompanyItemVo> itemVoList = request.getData();
        List<Integer> idList = itemVoList.stream().map(CompanyItemVo::getId).collect(Collectors.toList());
        companyService.removeByIds(idList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_C)
    public CommonResponse<Boolean> updateCompany(@RequestBody @Valid CommonRequest<CompanyItemVo> request) {
        CompanyDto dto = CommonUtils.copyProperties(request.getData(), CompanyDto.class);
        dto.setOldVersion(request.getData().getVersion());
        companyService.update(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UPDATE_FORM_C)
    public CommonResponse<CompanyListVo> getUpdateFormCompany(@RequestBody @Valid CommonRequest<Integer> request) {
        Company company = companyService.getById(request.getData());
        if (company == null) {
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        CompanyListVo listVo = CommonUtils.copyProperties(company, CompanyListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, listVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_C)
    public CommonResponse<PageVo<CompanyListVo>> queryCompany(@RequestBody @Valid CommonRequest<CompanyQueryVo> request) {
        CompanyDto dto = CommonUtils.copyProperties(request.getData(), CompanyDto.class);
        dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<CompanyListVo> page = new Page<>(dto.getCurrentPage(), dto.getPageSize());
        List<Company> companyList = companyService.queryCompany(dto);
        List<CompanyListVo> listVoList = CommonUtils.convertList(companyList, CompanyListVo.class);
        PageVo<CompanyListVo> pageVo = PageMapUtil.getPageMap(listVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_COMPANY_LIST)
    public CommonResponse<List> getCompanyList() {
        Integer judgeId = CommonUtils.judgeCompanyAndOrg();
        List<TreeListDto> dtoList = companyService.getCompanyTree(judgeId);
        List<TreeListVo> voList = CommonUtils.convertList(dtoList, TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, voList);
    }

}
