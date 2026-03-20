package exam.demo.modulebaseinfo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.modulebaseinfo.dto.SubjectTypeDto;
import exam.demo.modulebaseinfo.pojo.model.SubjectType;

import java.util.List;

/**
 * 题目类型服务接口
 * 提供题目类型的增删改查功能
 *
 * @author luohui
 */
public interface SubjectTypeService extends IService<SubjectType> {
    /**
     * 保存题目类型
     *
     * @param subjectTypeDto 题目类型信息
     * @return 是否保存成功
     */
    boolean save(SubjectTypeDto subjectTypeDto);

    /**
     * 删除题目类型列表
     *
     * @param subjectTypeList 待删除的题目类型列表
     * @return 是否删除成功
     */
    boolean remove(List<SubjectType> subjectTypeList);

    /**
     * 更新题目类型
     *
     * @param subjectType 题目类型信息
     * @return 是否更新成功
     */
    boolean update(SubjectTypeDto subjectType);

    /**
     * 查询题目类型列表
     *
     * @param subjectType 查询条件
     * @return 题目类型列表
     */
    List<SubjectType> list(SubjectType subjectType);

    /**
     * 查询题目类型更新表单
     *
     * @param subjectType 查询条件
     * @return 题目类型列表
     */
    List<SubjectType> querySubjectTypeUpdateForm(SubjectType subjectType);

    /**
     * 根据题目类型ID列表获取类型名称列表
     *
     * @param idList 题目类型ID列表
     * @return 类型名称列表
     */
    List<String> getTypeName(List<String> idList);

    /**
     * 根据题目类型ID获取类型名称
     *
     * @param id 题目类型ID
     * @return 类型名称
     */
    String getTypeName(String id);
}
