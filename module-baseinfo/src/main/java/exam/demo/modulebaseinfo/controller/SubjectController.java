package exam.demo.modulebaseinfo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulebaseinfo.constant.ControllerConstant;
import exam.demo.modulebaseinfo.exception.BaseInfoError;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.pojo.model.Subject;
import exam.demo.modulebaseinfo.pojo.model.SubjectAnswer;
import exam.demo.modulebaseinfo.pojo.model.SubjectInfo;
import exam.demo.modulebaseinfo.pojo.vo.SubjectAnswerQueryVo;
import exam.demo.modulebaseinfo.pojo.vo.SubjectQueryVo;
import exam.demo.modulebaseinfo.pojo.vo.SubjectVo;
import exam.demo.modulebaseinfo.service.SubjectAnswerService;
import exam.demo.modulebaseinfo.service.SubjectService;
import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 */
@RequestMapping(ControllerConstant.SUBJECT)
@RestController
public class SubjectController {
    @Autowired
    CommonState state;

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectAnswerService subjectAnswerService;


    @MethodEnhancer
    @PostMapping(ControllerConstant.SAVE_SUBJECT)
    public CommonResponse<Boolean> saveSubject(@RequestBody SubjectVo subjectVo) {
        SubjectDto subjectDto = CommonUtils.copyProperties(subjectVo, SubjectDto.class);
        List<SubjectAnswerDto> answerDtoList = CommonUtils.convertList(subjectVo.getSubjectAnswerVOList(), SubjectAnswerDto.class);
        subjectService.saveSubjectAndAnswer(subjectDto, answerDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.DELETE_SUBJECT_LIST)
    public CommonResponse<Boolean> deleteSubjectList(@RequestBody List<SubjectVo> subjectVoList) {
        List<Subject> subjects = CommonUtils.convertList(subjectVoList, Subject.class);
        for (Subject subject : subjects) {
            subject.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        subjectService.deleteSubjectAndAnswer(subjects);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.UPDATE_SUBJECT)
    public CommonResponse<Boolean> updateSubject(@RequestBody SubjectVo subjectVo) {
        SubjectDto subjectDto = CommonUtils.copyProperties(subjectVo, SubjectDto.class);
        List<SubjectAnswerDto> subjectAnswerDtoList = CommonUtils.convertList(subjectVo.getSubjectAnswerVOList(), SubjectAnswerDto.class);
        try {
            subjectService.updateSubject(subjectDto, subjectAnswerDtoList);
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.SUBJECT_UPDATE_FAIL);
        }
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_SUBJECT)
    public CommonResponse<PageVo<SubjectQueryVo>> querySubject(@RequestBody SubjectQueryVo queryVo) {
        Page<SubjectQueryVo> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        Subject subject = CommonUtils.copyProperties(queryVo, Subject.class);
        subject.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        List<SubjectInfo> subjectList = subjectService.listSubject(subject);
        List<SubjectQueryVo> voList = new ArrayList<>();
        for (SubjectInfo subjectInfo : subjectList) {
            voList.add(SubjectQueryVo.builder().id(subjectInfo.getId()).name(subjectInfo.getName()).difficulty(subjectInfo.getDifficulty())
                    .subjectTypeId(subjectInfo.getSubjectTypeId()).categoryId(subjectInfo.getCategoryId()).categoryName(subjectInfo.getCategoryName())
                    .subjectTypeName(subjectInfo.getSubjectTypeName()).difficultyName(subjectInfo.getDifficultyName()).updatedTime(subjectInfo.getUpdatedTime()).build()
            );
        }
        PageVo<SubjectQueryVo> pageVo = PageMapUtil.getPageMap(voList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_ANSWER)
    public CommonResponse<List> queryAnswer(@RequestBody SubjectAnswerQueryVo subjectAnswerQueryVo) {
        long subjectId = subjectAnswerQueryVo.getSubjectId();
        List<SubjectAnswer> answerList = subjectAnswerService.listAnswerBySubjectId(subjectId);
        List<SubjectAnswerQueryVo> voList = CommonUtils.convertList(answerList, SubjectAnswerQueryVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, voList);
    }
}
