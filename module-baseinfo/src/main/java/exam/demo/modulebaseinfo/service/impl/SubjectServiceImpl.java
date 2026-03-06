package exam.demo.modulebaseinfo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulebaseinfo.dao.SubjectDao;
import exam.demo.modulebaseinfo.exception.BaseInfoError;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.pojo.model.*;
import exam.demo.modulebaseinfo.service.CategoryService;
import exam.demo.modulebaseinfo.service.SubjectAnswerService;
import exam.demo.modulebaseinfo.service.SubjectService;
import exam.demo.modulebaseinfo.utils.DFSUtil;
import exam.demo.modulebaseinfo.utils.RandomTask;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.SubjectAnswerDto;
import exam.demo.modulecommon.common.SubjectDto;
import exam.demo.modulecommon.common.SubjectPackage;
import exam.demo.modulecommon.common.SubjectPackageDto;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.modulecommon.utils.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luohui
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, Subject> implements SubjectService {
    @Autowired
    SubjectAnswerService subjectAnswerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SnowFlake snowFlake;

    /**
     * 保存试题
     *
     * @param dto
     * @param subjectAnswerDtoList
     * @return
     */
    @FullCommonField
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveSubjectAndAnswer(SubjectDto dto, List<SubjectAnswerDto> subjectAnswerDtoList) {
        Subject subject = CommonUtils.copyProperties(dto, Subject.class);
        List<SubjectAnswer> answerList = CommonUtils.convertList(subjectAnswerDtoList, SubjectAnswer.class);
        for (SubjectAnswer subjectAnswer : answerList) {
            subjectAnswer.setId(snowFlake.nextId());
            subjectAnswer.setSubjectId(dto.getId());
        }
        return save(subject) && subjectAnswerService.saveBatch(answerList);
    }

    /**
     * 删除试题，同时删除答案
     *
     * @param subjectList
     * @return
     */
    //@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSubjectAndAnswer(List<Subject> subjectList) {
        subjectAnswerService.removeBatchBySubjectId(subjectList.stream().map(Subject::getId).collect(Collectors.toList()));
        return removeByIds(subjectList.stream().map(Subject::getId).collect(Collectors.toList()));
    }

    /**
     * 首先删除原答案，然后更新题目信息，最后插入新增答案
     *
     * @param subjectDto
     * @param answerDtoList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @FullCommonField(operation = EnumOperation.UPDATE)
    @Override
    public boolean updateSubject(SubjectDto subjectDto, List<SubjectAnswerDto> answerDtoList) {
        subjectAnswerService.removeBySubjectId(subjectDto.getId());
        List<SubjectAnswer> subjectAnswerList = CommonUtils.convertList(answerDtoList, SubjectAnswer.class);
        for (SubjectAnswer subjectAnswer : subjectAnswerList) {
            subjectAnswer.setId(snowFlake.nextId());
            subjectAnswer.setSubjectId(subjectDto.getId());
        }
        subjectAnswerService.saveBatch(subjectAnswerList);
        Subject subject = CommonUtils.copyProperties(subjectDto, Subject.class);
        updateById(subject);
        return true;
    }

    /**
     * @param subject
     * @return
     */
    @Override
    public List<SubjectInfo> listSubject(Subject subject) {
        // 如果题目类型是根节点，则返回所有的信息
        List<SubjectInfo> subjectInfoList = new ArrayList<>();
        if (subject.getCategoryId() != null) {
            Category category = categoryService.getById(subject.getCategoryId());
            if (category != null) {
                // 使用DFSUtil递归查找子节点
                DFSUtil<Category> dfs = new DFSUtil<>(category.getId(), categoryService::queryChildNode);
                List<Long> res = dfs.getRes();
                for (Long id : res) {
                    // 创建临时Subject对象，设置categoryId和其他查询条件
                    Subject tempSubject = new Subject();
                    tempSubject.setCategoryId(id);
                    tempSubject.setName(subject.getName());
                    tempSubject.setSubjectTypeId(subject.getSubjectTypeId());
                    tempSubject.setDifficulty(subject.getDifficulty());
                    tempSubject.setJudgeId(subject.getJudgeId());
                    subjectInfoList.addAll(baseMapper.queryByCategory(tempSubject));
                }
            }
        } else {
            // 检查 judgeId 是否为 null
            // 无论 judgeId 是否为 null，都调用 querySubject 方法
            // 因为 querySubject 方法已经在 SQL 中处理了 judgeId 为 null 的情况
            subjectInfoList = baseMapper.querySubject(subject);
        }
        return subjectInfoList;
    }


    @Override
    public SubjectPackage getSubject(List<CombExamConfigDetail> itemList) {
        List<SubjectPackageDto> dtoList = new ArrayList<>();
        for (CombExamConfigDetail item : itemList) {
            // 随机取题
            List<Long> subjectIdList = getAssignedIdList(item);
            // 获取试题
            List<Subject> subjectList = baseMapper.querySubjectByPrimaryKeyList(subjectIdList);
            // 获取答案
            List<SubjectAnswer> subjectAnswerList = subjectAnswerService.listAnswer(subjectList.stream().map(Subject::getId).collect(Collectors.toList()));
            if (CommonUtils.isEmpty(subjectList)) {
                throw new BaseInfoException(BaseInfoError.GENERATE_FAIL);
            }
            if (CommonUtils.isEmpty(subjectAnswerList)) {
                throw new BaseInfoException(BaseInfoError.GENERATE_FAIL);
            }
            for (Subject subject : subjectList) {
                SubjectPackageDto packageDto = new SubjectPackageDto();
                SubjectDto dto = CommonUtils.copyProperties(subject, SubjectDto.class);
                dto.setScore(item.getScore());
                packageDto.setSubjectDTO(dto);
                List<SubjectAnswerDto> subjectAnswerDtoList = subjectAnswerList.stream()
                        .filter(s -> s.getSubjectId().equals(subject.getId()))
                        .map(s -> CommonUtils.copyProperties(s, SubjectAnswerDto.class)).collect(Collectors.toList());
                packageDto.setSubjectAnswerDtoList(subjectAnswerDtoList);
                dtoList.add(packageDto);
            }
        }
        SubjectPackage subjectPackageList = new SubjectPackage();
        subjectPackageList.setDtoList(dtoList);
        return subjectPackageList;
    }

    @Override
    public SubjectPackage getSubjectById(List<Long> idList) {
        List<SubjectPackageDto> dtoList = new ArrayList<>();
        // 获取试题
        List<Subject> subjectList = baseMapper.querySubjectByPrimaryKeyList(idList);
        // 获取答案
        List<SubjectAnswer> subjectAnswerList = subjectAnswerService.listAnswer(subjectList.stream().map(Subject::getId).collect(Collectors.toList()));
        if (CommonUtils.isEmpty(subjectList)) {
            throw new BaseInfoException(BaseInfoError.GENERATE_FAIL);
        }
        if (CommonUtils.isEmpty(subjectAnswerList)) {
            throw new BaseInfoException(BaseInfoError.GENERATE_FAIL);
        }

        for (Subject subject : subjectList) {
            SubjectPackageDto packageDto = new SubjectPackageDto();
            SubjectDto dto = CommonUtils.copyProperties(subject, SubjectDto.class);
            packageDto.setSubjectDTO(dto);
            List<SubjectAnswerDto> subjectAnswerDtoList = subjectAnswerList.stream()
                    .filter(s -> s.getSubjectId().equals(subject.getId()))
                    .map(s -> CommonUtils.copyProperties(s, SubjectAnswerDto.class)).collect(Collectors.toList());
            packageDto.setSubjectAnswerDtoList(subjectAnswerDtoList);
            dtoList.add(packageDto);
        }
        return SubjectPackage.builder().dtoList(dtoList).build();
    }

    /**
     * 获取指定配置的题目
     *
     * @param item
     * @return
     */
    private List<Long> getAssignedIdList(CombExamConfigDetail item) {
        Subject subject = CommonUtils.copyProperties(item, Subject.class);
        List<Long> currentConfigId = baseMapper.querySubjectIdList(subject);
        if (currentConfigId.size() > item.getNum()) {
            return new RandomTask(item.getNum(), currentConfigId).gen();
        }
        throw new BaseInfoException(BaseInfoError.ACQUIRE_ID_FAIL, item.getCategory(), item.getDifficultyName(), item.getSubjectType());
    }

    @Override
    public void isEnough(Long category, Long subjectType, int count) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        if (category == null || subjectType == null) {
            throw new BaseInfoException(BaseInfoError.CONFIG_INVALID);
        }
        wrapper.eq("category_id", category);
        wrapper.eq("subject_type_id", subjectType);
        if (count(wrapper) < count) {
            Category category1 = categoryService.getById(category);
            throw new BaseInfoException(BaseInfoError.SUBJECT_NOT_ENOUGH, category1.getName());
        }
    }
}
