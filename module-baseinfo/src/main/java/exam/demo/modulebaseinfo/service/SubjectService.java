package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import exam.demo.modulebaseinfo.pojo.model.Subject;
import exam.demo.modulebaseinfo.pojo.model.SubjectInfo;
import exam.demo.modulecommon.common.SubjectAnswerDto;
import exam.demo.modulecommon.common.SubjectDto;
import exam.demo.modulecommon.common.SubjectPackage;

import java.util.List;

/**
 * @author luohui
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 保存试题
     *
     * @param dto
     * @param subjectAnswerDtoList
     * @return
     */
    boolean saveSubjectAndAnswer(SubjectDto dto, List<SubjectAnswerDto> subjectAnswerDtoList);

    /**
     * 删除试题，同时删除答案
     *
     * @param subjectList
     * @return
     */
    boolean deleteSubjectAndAnswer(List<Subject> subjectList);

    /**
     * @param subjectDto
     * @param answerDtoList
     * @return
     */
    boolean updateSubject(SubjectDto subjectDto, List<SubjectAnswerDto> answerDtoList);

    /**
     * @param subject
     * @return
     */
    List<SubjectInfo> listSubject(Subject subject);

    SubjectPackage getSubject(List<CombExamConfigDetail> itemList);

    SubjectPackage getSubjectById(List<Long> idList);

    /**
     * 判断当前配置是否满足题目数目
     *
     * @param category
     * @param subjectType
     * @param count
     * @return
     */
    void isEnough(Long category, Long subjectType, int count);
}
