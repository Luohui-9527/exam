package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.DepartmentDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.Department;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.vo.DepartmentItemVo;
import exam.demo.moduleuser.pojo.vo.DepartmentListVo;
import exam.demo.moduleuser.pojo.vo.DepartmentQueryVo;
import exam.demo.moduleuser.pojo.vo.TreeListVo;
import exam.demo.moduleuser.service.IDepartmentService;
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
@RequestMapping(ControllerConstants.DEPARTMENT)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_D)
    public CommonResponse<Boolean> saveDepartment(@RequestBody @Valid DepartmentItemVo departmentItemVo) {
        DepartmentDto departmentDto = CommonUtils.copyProperties(departmentItemVo, DepartmentDto.class);
        departmentService.save(departmentDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_D)
    public CommonResponse<Boolean> updateDepartment(@RequestBody @Valid DepartmentItemVo departmentItemVo) {
        DepartmentDto departmentDto = CommonUtils.copyProperties(departmentItemVo, DepartmentDto.class);
        departmentDto.setOldVersion(departmentDto.getVersion());
        departmentService.update(departmentDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_D)
    public CommonResponse<Boolean> deleteDepartment(@RequestBody @Valid List<DepartmentItemVo> departmentItemVoList) {
        List<DepartmentDto> departmentDtoList = CommonUtils.convertList(departmentItemVoList, DepartmentDto.class);
        departmentService.delete(departmentDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_D)
    public CommonResponse<DepartmentListVo> getUpdateFormDepartment(@RequestBody Long departmentId) {
        Department department = departmentService.getById(departmentId);
        if (department == null) {
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        DepartmentListVo departmentListVo = CommonUtils.copyProperties(department, DepartmentListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, departmentListVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_D)
    public CommonResponse<PageVo<DepartmentListVo>> queryDepartment(@RequestBody @Valid DepartmentQueryVo departmentQueryVo) {
        Department department = CommonUtils.copyProperties(departmentQueryVo, Department.class);
        department.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<DepartmentListVo> page = new Page<>(departmentQueryVo.getCurrentPage(), departmentQueryVo.getTotalPages());
        List<Department> list = departmentService.query(department);
        List<DepartmentListVo> voList = CommonUtils.convertList(list, DepartmentListVo.class);
        PageVo<DepartmentListVo> pageVo = PageMapUtil.getPageMap(voList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_LEVEL)
    public CommonResponse<List> queryLevelDepartment() {
        List<Department> departmentList = departmentService.queryLevel();
        List<DepartmentQueryVo> res = CommonUtils.convertList(departmentList, DepartmentQueryVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, res);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_PARENT)
    public CommonResponse<List> queryParentDepartment() {
        List<Department> departmentList = departmentService.queryParent();
        List<DepartmentQueryVo> res = CommonUtils.convertList(departmentList, DepartmentQueryVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, res);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.GET_DEP_TREE_DATA)
    public CommonResponse<List> queryTreeDataDepartment() {
        List<TreeList> departmentList = departmentService.queryTreeData();
        List<TreeListVo> res = CommonUtils.convertList(departmentList, TreeListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, res);
    }

}
