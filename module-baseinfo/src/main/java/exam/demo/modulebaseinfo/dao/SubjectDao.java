package exam.demo.modulebaseinfo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulebaseinfo.pojo.model.Subject;
import exam.demo.modulebaseinfo.pojo.model.SubjectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目数据访问接口
 *
 * @author luohui
 */
@Mapper
public interface SubjectDao extends BaseMapper<Subject> {
    /**
     * 查询题目列表
     *
     * @param subject 题目查询条件
     * @return 题目信息列表
     */
    List<SubjectInfo> querySubject(@Param("subject") Subject subject);

    /**
     * 根据分类ID查询题目（带分页）
     *
     * @param page 分页对象
     * @param subject 题目查询条件
     * @return 分页后的题目信息列表
     */
    IPage<SubjectInfo> queryByCategory(Page<SubjectInfo> page, @Param("subject") Subject subject);

    /**
     * 根据分类ID列表查询题目（带分页）- 真正的数据库分页
     *
     * @param page 分页对象
     * @param categoryIds 分类ID列表
     * @param subject 题目查询条件（其他条件）
     * @return 分页后的题目信息列表
     */
    IPage<SubjectInfo> queryByCategoryIds(Page<SubjectInfo> page, @Param("categoryIds") List<String> categoryIds, @Param("subject") Subject subject);

    /**
     * 根据分类ID查询题目（非分页）
     *
     * @param subject 题目查询条件
     * @return 题目信息列表
     */
    List<SubjectInfo> queryByCategoryList(@Param("subject") Subject subject);

    /**
     * 查询题目ID列表
     *
     * @param subject 题目查询条件
     * @return 题目ID列表
     */
    List<Long> querySubjectIdList(Subject subject);

    /**
     * 通过id来查询Subject
     *
     * @param idList 题目ID列表
     * @return 题目列表
     */
    List<Subject> querySubjectByPrimaryKeyList(@Param("idList") List<Long> idList);
}
