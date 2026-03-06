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
 * 题目服务接口
 * 提供题目的增删改查、批量操作等功能
 *
 * @author luohui
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 保存试题及答案
     *
     * @param dto 题目信息
     * @param subjectAnswerDtoList 题目答案列表
     * @return 是否保存成功
     */
    boolean saveSubjectAndAnswer(SubjectDto dto, List<SubjectAnswerDto> subjectAnswerDtoList);

    /**
     * 删除试题，同时删除关联的答案
     *
     * @param subjectList 待删除的题目列表
     * @return 是否删除成功
     */
    boolean deleteSubjectAndAnswer(List<Subject> subjectList);

    /**
     * 更新试题及答案
     * 首先删除原答案，然后更新题目信息，最后插入新增答案
     *
     * @param subjectDto 题目信息
     * @param answerDtoList 题目答案列表
     * @return 是否更新成功
     */
    boolean updateSubject(SubjectDto subjectDto, List<SubjectAnswerDto> answerDtoList);

    /**
     * 查询题目列表
     * 支持按分类、题型、难度等条件过滤
     *
     * @param subject 查询条件
     * @return 题目信息列表
     */
    List<SubjectInfo> listSubject(Subject subject);

    /**
     * 根据配置获取题目包
     * 用于生成试卷
     *
     * @param itemList 试卷配置详情列表
     * @return 题目包
     */
    SubjectPackage getSubject(List<CombExamConfigDetail> itemList);

    /**
     * 根据题目ID列表获取题目包
     *
     * @param idList 题目ID列表
     * @return 题目包
     */
    SubjectPackage getSubjectById(List<Long> idList);

    /**
     * 判断当前配置是否满足题目数目要求
     *
     * @param category 分类ID
     * @param subjectType 题型ID
     * @param count 题目数量
     * @throws exam.demo.modulebaseinfo.exception.BaseInfoException 如果题目数量不足
     */
    void isEnough(Long category, Long subjectType, int count);
}
