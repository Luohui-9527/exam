package exam.demo.modulepaper.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulecommon.common.FuzzySearch;
import exam.demo.modulecommon.common.PaperDetail;
import exam.demo.modulecommon.common.PaperIdWithName;
import exam.demo.modulepaper.pojo.dto.ModifyPaperDto;
import exam.demo.modulepaper.pojo.dto.PaperDto;
import exam.demo.modulepaper.pojo.dto.PaperQueryDto;
import exam.demo.modulepaper.pojo.model.Paper;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;
import exam.demo.modulepaper.pojo.vo.CustomizedCombExamConfigVo;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface PaperService extends IService<Paper> {


    /**
     * 快速组卷
     *
     * @param paperDTO
     * @param paperDTO
     * @return 成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean generateFastMode(PaperDto paperDTO);

    /**
     * 标准组卷
     *
     * @param paperDTO
     * @param combExamConfigDTO
     * @return
     */
    boolean generateNormalMode(PaperDto paperDTO, CustomizedCombExamConfigVo combExamConfigDTO);

    /**
     * 模版组卷
     *
     * @param paperDTO
     * @return
     */
    boolean generateTemplateMode(PaperDto paperDTO);


    /**
     * 插入试卷
     *
     * @param paper
     * @param subjectList
     * @param subjectAnswerList
     * @return
     */
    boolean insertPaper(Paper paper, List<PaperSubject> subjectList, List<PaperSubjectAnswer> subjectAnswerList);

    /**
     * 通过查询参数查询试卷
     *
     * @param paperQueryDTO 查询试卷参数
     * @return 返回满足此参数的试卷列表
     */
    Map<String, Object> queryPaper(PaperQueryDto paperQueryDTO, boolean isTemplate);

    /**
     * 准备要删除的试卷数据
     *
     * @param paperIds 试卷id数组
     * @return 删除成功的条数
     */
    boolean paperDelete(List<Integer> paperIds);

    /**
     * 准备修改的资源
     *
     * @param paperDetail
     * @return
     */
    boolean paperModify(ModifyPaperDto paperDetail);

    /**
     * 获取试卷详情
     *
     * @param id
     * @return
     */
    PaperDetail getPaperInfo(long id);

    /**
     * download
     *
     * @param paper the ids of which are downloaded
     * @return the count of downloaded
     * @throws Exception when parse a userPermission if token is invalid of expired and decode unsuccessfully
     */
    boolean downloadTemplate(PaperDto paper);

    /**
     * upload
     *
     * @param paper the id of uploaded
     * @return the count of uploaded
     * @throws Exception when parse a userPermission if token is invalid of expired and decode unsuccessfully
     */
    boolean uploadTemplate(PaperDto paper);

    /**
     * remove batch of templates
     *
     * @param paperTemplateIds the paper id of a template
     * @return the count of removed paper
     */
    boolean deleteTemplate(List<Integer> paperTemplateIds);

    /**
     * 查询模板
     *
     * @param paperQueryDTO
     * @return
     */
    Map<String, Object> queryTemplate(PaperQueryDto paperQueryDTO);

    /**
     * 发布试卷，也就是将试卷中publishedTime加一
     *
     * @param id
     * @return
     */
    boolean publish(long id);

    /**
     * 列出该公司的所有试卷
     *
     * @param companyId
     * @return
     */
    List<PaperIdWithName> list(long companyId);

    /**
     * 通过试卷名模糊搜索
     *
     * @param fuzzySearch
     * @return
     */
    List<PaperIdWithName> listByName(FuzzySearch fuzzySearch);

    /**
     * 获取试卷
     */
    Paper getPaper(long id);

    /**
     * 获取试卷分数
     *
     * @param id
     * @return
     */
    Double getScore(long id);
}
