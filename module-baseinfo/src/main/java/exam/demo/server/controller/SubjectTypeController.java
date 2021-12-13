package exam.demo.server.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.server.biz.service.SubjectTypeService;
import exam.demo.server.constant.ControllerConstant;
import exam.demo.server.dto.SubjectTypeDto;
import exam.demo.server.pojo.model.SubjectType;
import exam.demo.server.pojo.vo.SubjectQueryVo;
import exam.demo.server.pojo.vo.SubjectTypeQueryVo;
import exam.demo.server.pojo.vo.SubjectTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luohui
 */
@RequestMapping(ControllerConstant.SUBJECT_TYPE)
@RestController
public class SubjectTypeController {
    @Autowired
    SubjectTypeService subjectTypeService;

    @Autowired
    CommonState state;

    @MethodEnhancer
    @PostMapping(ControllerConstant.SAVE_SUBJECT_TYPE)
    public CommonResponse<Boolean> saveSubjectType(@RequestBody SubjectTypeVo subjectTypeVo) {
        SubjectTypeDto subjectTypeDto = CommonUtils.copyProperties(subjectTypeVo, SubjectTypeDto.class);
        subjectTypeService.save(subjectTypeDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.DELETE_SUBJECT_TYPE)
    public CommonResponse<Boolean> deleteSubjectType(@RequestBody List list) {
        List<SubjectType> subjectTypeList = CommonUtils.convertList(list, SubjectType.class);
        UserPermission userPermission = TokenUtils.getUser();
        for (SubjectType type : subjectTypeList) {
            type.setOrgId(userPermission.getOrgId());
        }
        subjectTypeService.remove(subjectTypeList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.UPDATE_SUBJECT_TYPE)
    public CommonResponse<Boolean> updateSubjectType(@RequestBody SubjectTypeVo subjectTypeVo) {
        SubjectTypeDto subjectTypeDto = CommonUtils.copyProperties(subjectTypeVo, SubjectTypeDto.class);
        subjectTypeDto.setJudgeId(TokenUtils.getUser().getOrgId());
        subjectTypeDto.setOldVersion(subjectTypeDto.getVersion());
        subjectTypeService.update(subjectTypeDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_SUBJECT_TYPE)
    public CommonResponse<PageVo<SubjectQueryVo>> querySubjectType(@RequestBody SubjectTypeQueryVo queryVo) {
        Page<SubjectTypeQueryVo> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        SubjectType subjectType = CommonUtils.copyProperties(queryVo, SubjectType.class);
        subjectType.setOrgId(TokenUtils.getUser().getOrgId());
        List<SubjectType> subjectTypeList = subjectTypeService.list(subjectType);
        List<SubjectQueryVo> subjectQueryVoList = CommonUtils.convertList(subjectTypeList, SubjectQueryVo.class);
        PageVo<SubjectQueryVo> pageVo = PageMapUtil.getPageMap(subjectQueryVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_SUBJECT_TYPE_UPDATE_FORM)
    public CommonResponse<List> querySubjectTypeUpdateForm(@RequestBody SubjectTypeQueryVo queryVo) {
        SubjectType subjectType = CommonUtils.copyProperties(queryVo, SubjectType.class);
        subjectType.setOrgId(TokenUtils.getUser().getOrgId());
        List<SubjectType> subjectTypeList = subjectTypeService.querySubjectTypeUpdateForm(subjectType);
        List<SubjectTypeVo> subjectTypeVoList = CommonUtils.convertList(subjectTypeList, SubjectTypeVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, subjectTypeVoList);
    }
}
