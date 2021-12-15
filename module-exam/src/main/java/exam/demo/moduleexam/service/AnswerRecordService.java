package exam.demo.moduleexam.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleexam.pojo.DTO.dopaper.DoPaperFormDTO;
import exam.demo.moduleexam.pojo.model.AnswerRecord;

import java.util.List;


public interface AnswerRecordService extends IService<AnswerRecord> {

    /**
     * 功能描述
     *
     * @param doPaperFormDTO
     * @return: exam.demo.moduleexam.pojo.model.AnswerRecord
     * @Author: luohui
     * @date: 2021-12-10
     */
    AnswerRecord getOneByDoPaperFormDTO(DoPaperFormDTO doPaperFormDTO);

    /**
     * 功能描述
     *
     * @param paperSubjectId
     * @param examRecordId
     * @return: exam.demo.moduleexam.pojo.model.AnswerRecord
     * @Author: luohui
     * @date: 2021-12-10
     */
    AnswerRecord getOneByPaperSubjectIdAndExamRecordId(Integer paperSubjectId, Integer examRecordId);

    /**
     * 功能描述: 根据考试记录id获取答卷明细
     *
     * @param examRecordId 考试记录ID
     * @return: java.util.List<exam.demo.moduleexam.pojo.model.AnswerRecord>
     * @Author: luohui
     * @date: 2021-12-10
     */
    List<AnswerRecord> getListByExamRecordId(Integer examRecordId);
}
