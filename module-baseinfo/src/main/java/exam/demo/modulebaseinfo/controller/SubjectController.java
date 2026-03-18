package exam.demo.modulebaseinfo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulebaseinfo.exception.BaseInfoError;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.pojo.model.Subject;
import exam.demo.modulebaseinfo.pojo.model.SubjectAnswer;
import exam.demo.modulebaseinfo.pojo.model.SubjectInfo;
import exam.demo.modulebaseinfo.pojo.vo.SubjectAnswerQueryVo;
import exam.demo.modulebaseinfo.pojo.vo.SubjectQueryResultVo;
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

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    CommonState state;

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectAnswerService subjectAnswerService;

    /**
     * 保存试题及答案
     *
     * @param subjectVo 题目信息
     * @return 保存结果
     */
    @MethodEnhancer
    @PostMapping("/save")
    public CommonResponse<Boolean> saveSubject(@RequestBody SubjectVo subjectVo) {
        SubjectDto subjectDto = CommonUtils.copyProperties(subjectVo, SubjectDto.class);
        List<SubjectAnswerDto> answerDtoList = CommonUtils.convertList(subjectVo.getSubjectAnswerVOList(), SubjectAnswerDto.class);
        subjectService.saveSubjectAndAnswer(subjectDto, answerDtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    /**
     * 批量删除试题及答案
     *
     * @param subjectVoList 待删除的题目列表
     * @return 删除结果
     */
    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> deleteSubjectList(@RequestBody List<SubjectVo> subjectVoList) {
        List<Subject> subjects = CommonUtils.convertList(subjectVoList, Subject.class);
        for (Subject subject : subjects) {
            subject.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        subjectService.deleteSubjectAndAnswer(subjects);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    /**
     * 更新试题及答案
     *
     * @param subjectVo 题目信息
     * @return 更新结果
     * @throws BaseInfoException 如果更新失败
     */
    @MethodEnhancer
    @PostMapping("/update")
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

    /**
     * 查询题目列表
     *
     * @param queryVo 查询条件
     * @return 分页查询结果
     */
    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<SubjectQueryResultVo>> querySubject(@RequestBody SubjectQueryVo queryVo) {
        Long pageNum = queryVo.getPageNum() != null ? queryVo.getPageNum() : 1L;
        Long pageSize = queryVo.getPageSize() != null ? queryVo.getPageSize() : 10L;
        Page<SubjectQueryResultVo> page = new Page<>(pageNum, pageSize);
        Subject subject = CommonUtils.copyProperties(queryVo, Subject.class);
        try {
            subject.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        } catch (Exception e) {
            // 没有登录时，不设置 judgeId
        }
        List<SubjectInfo> subjectList = subjectService.listSubject(subject);
        List<SubjectQueryResultVo> resultList = new ArrayList<>();
        for (SubjectInfo subjectInfo : subjectList) {
            SubjectQueryResultVo resultVo = new SubjectQueryResultVo();
            resultVo.setId(subjectInfo.getId());
            resultVo.setName(subjectInfo.getName());
            resultVo.setDifficulty(subjectInfo.getDifficulty());
            resultVo.setSubjectTypeId(subjectInfo.getSubjectTypeId());
            resultVo.setCategoryId(subjectInfo.getCategoryId());
            resultVo.setCategoryName(subjectInfo.getCategoryName());
            resultVo.setSubjectTypeName(subjectInfo.getSubjectTypeName());
            resultVo.setDifficultyName(subjectInfo.getDifficultyName());
            resultVo.setUpdatedTime(subjectInfo.getUpdatedTime());
            resultList.add(resultVo);
        }
        PageVo<SubjectQueryResultVo> pageVo = PageMapUtil.getPageMap(resultList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    /**
     * 查询题目答案
     *
     * @param subjectAnswerQueryVo 查询条件
     * @return 答案列表
     */
    @MethodEnhancer
    @PostMapping("/answer")
    public CommonResponse<List> queryAnswer(@RequestBody SubjectAnswerQueryVo subjectAnswerQueryVo) {
        long subjectId = subjectAnswerQueryVo.getSubjectId();
        List<SubjectAnswer> answerList = subjectAnswerService.listAnswerBySubjectId(subjectId);
        List<SubjectAnswerQueryVo> voList = CommonUtils.convertList(answerList, SubjectAnswerQueryVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, voList);
    }
}
