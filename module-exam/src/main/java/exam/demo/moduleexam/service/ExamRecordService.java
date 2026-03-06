package exam.demo.moduleexam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleexam.pojo.model.ExamRecord;

import java.util.List;


/**
 * 考试记录服务接口
 * 提供考试记录的查询、管理等功能
 *
 * @author luohui
 */
public interface ExamRecordService extends IService<ExamRecord> {

    /**
     * 通过电话获取考试记录
     *
     * @param tel 电话
     * @return 考试记录
     */
    ExamRecord getOneByTel(String tel);

    /**
     * 根据考试发布记录ID获取考试记录列表
     *
     * @param examPublishRecordId 考试发布记录ID
     * @return 考试记录列表
     */
    List<ExamRecord> getListByExamPublishRecordId(Long examPublishRecordId);
}
