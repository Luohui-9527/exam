package exam.demo.moduleexam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleexam.pojo.model.ExamRecord;

import java.util.List;


public interface ExamRecordService extends IService<ExamRecord> {

    /**
     * 功能描述: 通过电话获取考试记录
     *
     * @param tel 电话
     * @return: exam.demo.moduleexam.pojo.model.ExamRecord
     * @Author: luohui
     * @date: 2021-12-10
     */
    ExamRecord getOneByTel(String tel);

    /**
     * 功能描述: 获取考试记录列表
     *
     * @param examPublishRecordId
     * @return: java.util.List<exam.demo.moduleexam.pojo.model.ExamRecord>
     * @Author: luohui
     * @date: 2021-12-13
     */
    List<ExamRecord> getListByExamPublishRecordId(Integer examPublishRecordId);
}
