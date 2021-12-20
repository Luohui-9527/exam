package exam.demo.moduleexam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleexam.pojo.DTO.grade.ExamGradeRecordQueryFormDTO;
import exam.demo.moduleexam.pojo.DTO.publish.*;
import exam.demo.moduleexam.pojo.model.ExamPublishRecord;

import java.util.Date;
import java.util.List;


public interface ExamPublishRecordService extends IService<ExamPublishRecord> {
    /**
     * 获取考试发布记录
     *
     * @param examPublishRecordQueryFormDTO
     * @return
     */
    List<ExamPublishRecordTableDataDTO> queryExamPublishRecord(ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO);

    /**
     * 添加一条考试发布记录
     *
     * @param examPublishRecordPublishFormDTO
     * @return true or false
     */
    boolean addExamPublishRecord(ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO);

    /**
     * 删除考试发布记录
     *
     * @param dtoList
     * @return
     */
    boolean deleteExamPublishRecord(List<ExamPublishRecordDeleteFormDTO> dtoList);

    /**
     * 修改考试发布记录信息
     *
     * @param examPublishRecordEditFormDTO
     * @return
     */
    boolean updateExamPublishRecord(ExamPublishRecordEditFormDTO examPublishRecordEditFormDTO);

    /**
     * 功能描述: 获取考试发布记录
     *
     * @param id      id
     * @param version 版本
     * @return: java.util.List<exam.demo.moduleexam.pojo.model.ExamPublishRecord>
     * @Author: luohui
     * @date: 2021-12-10
     */
    List<ExamPublishRecord> getListByIdVersion(Long id, Long version);

    /**
     * 功能描述
     *
     * @param examGradeRecordQueryFormDTO
     * @return: java.util.List<exam.demo.moduleexam.pojo.model.ExamPublishRecord>
     * @Author: luohui
     * @date: 2021-12-10
     */
    List<ExamPublishRecord> queryGrade(ExamGradeRecordQueryFormDTO examGradeRecordQueryFormDTO);

    /**
     * 功能描述: 获取所有考试发布记录
     *
     * @param title
     * @param examSession
     * @param startTime
     * @param endTime
     * @return: java.util.List<exam.demo.moduleexam.pojo.model.ExamPublishRecord>
     * @Author: luohui
     * @date: 2021-12-13
     */
    List<ExamPublishRecord> getExamPublishRecordList(String title, Long examSession, Date startTime, Date endTime);
}
