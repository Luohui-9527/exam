package exam.demo.modulepaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulecommon.common.*;
import exam.demo.modulepaper.pojo.dto.ModifyPaperDto;
import exam.demo.modulepaper.pojo.dto.PaperDto;
import exam.demo.modulepaper.pojo.dto.PaperQueryDto;
import exam.demo.modulepaper.pojo.model.Paper;
import exam.demo.modulepaper.pojo.model.PaperSubject;
import exam.demo.modulepaper.pojo.model.PaperSubjectAnswer;
import exam.demo.modulepaper.pojo.vo.CustomizedCombExamConfigVo;
import exam.demo.modulepaper.pojo.vo.PaperQueryVo;

import java.util.List;
import java.util.Map;

/**
 * 试卷服务接口
 * 提供试卷的创建、查询、修改、删除、发布等功能
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
public interface IPaperService extends IService<Paper> {

    /**
     * 快速组卷
     *
     * @param paperDTO 试卷信息
     * @return 成功返回试卷ID，否则返回null
     */
    String generateFastMode(PaperDto paperDTO);

    /**
     * 标准组卷
     *
     * @param paperDTO          试卷信息
     * @param combExamConfigDTO 组卷配置信息
     * @return 成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean generateNormalMode(PaperDto paperDTO, CustomizedCombExamConfigVo combExamConfigDTO);

    /**
     * 模版组卷
     *
     * @param paperDTO 试卷信息
     * @return 成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean generateTemplateMode(PaperDto paperDTO);

    /**
     * 插入试卷
     *
     * @param paper             试卷信息
     * @param subjectList       试卷题目列表
     * @param subjectAnswerList 试卷答案列表
     * @return 成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean insertPaper(Paper paper, List<PaperSubject> subjectList, List<PaperSubjectAnswer> subjectAnswerList);

    /**
     * 通过查询参数查询试卷
     *
     * @param paperQueryDTO 查询试卷参数
     * @param isTemplate    是否为模板
     * @return 返回满足此参数的试卷列表
     */
    Map<String, Object> queryPaper(PaperQueryDto paperQueryDTO, boolean isTemplate);

    /**
     * 删除试卷
     *
     * @param paperIds 试卷id数组
     * @return 删除成功的条数
     */
    boolean paperDelete(List<String> paperIds);

    /**
     * 修改试卷
     *
     * @param paperDetail 试卷详情
     * @return 成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean paperModify(ModifyPaperDto paperDetail);

    /**
     * 获取试卷详情
     *
     * @param id 试卷ID
     * @return 试卷详情
     */
    PaperDetail getPaperInfo(String id);

    /**
     * 下载模板
     *
     * @param paper 试卷信息
     * @return 下载成功返回 <code>true</code> 否则 <code>false</code>
     * @throws Exception 当解析用户权限失败时
     */
    boolean downloadTemplate(PaperDto paper);

    /**
     * 上传模板
     *
     * @param paper 试卷信息
     * @return 上传成功返回 <code>true</code> 否则 <code>false</code>
     * @throws Exception 当解析用户权限失败时
     */
    boolean uploadTemplate(PaperDto paper);

    /**
     * 批量删除模板
     *
     * @param paperTemplateIds 模板试卷ID列表
     * @return 删除成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean deleteTemplate(List<String> paperTemplateIds);

    /**
     * 查询模板
     *
     * @param paperQueryDTO 查询参数
     * @return 模板列表
     */
    Map<String, Object> queryTemplate(PaperQueryDto paperQueryDTO);

    /**
     * 发布试卷，也就是将试卷中publishedTime加一
     *
     * @param id 试卷ID
     * @return 发布成功返回 <code>true</code> 否则 <code>false</code>
     */
    boolean publish(String id);

    /**
     * 列出该公司的所有试卷
     *
     * @param companyId 公司ID
     * @return 试卷ID和名称列表
     */
    List<PaperIdWithName> list(String companyId);

    /**
     * 列出该公司的所有试卷（详细信息）
     *
     * @param companyId 公司ID
     * @param queryVo   分页查询参数
     * @return 试卷列表
     */
    PageVo<PaperListVo> listVo(String companyId, PaperQueryVo queryVo);

    /**
     * 通过试卷名模糊搜索
     *
     * @param fuzzySearch 模糊搜索条件
     * @return 试卷ID和名称列表
     */
    List<PaperIdWithName> listByName(FuzzySearch fuzzySearch);

    /**
     * 获取试卷
     *
     * @param id 试卷ID
     * @return 试卷信息
     */
    Paper getPaper(String id);

    /**
     * 获取试卷分数
     *
     * @param id 试卷ID
     * @return 试卷分数
     */
    Double getScore(String id);
}
