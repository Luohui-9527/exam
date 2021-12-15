package exam.demo.server.biz.service;


import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.server.dto.SubjectTypeDto;
import exam.demo.server.pojo.model.SubjectType;

import java.util.List;

/**
 * @author luohui
 */
public interface SubjectTypeService extends IService<SubjectType> {
    boolean save(SubjectTypeDto subjectTypeDto);

    boolean remove(List<SubjectType> subjectTypeList);

    boolean update(SubjectTypeDto subjectType);

    List<SubjectType> list(SubjectType subjectType);

    List<SubjectType> querySubjectTypeUpdateForm(SubjectType subjectType);

    List<String> getTypeName(List<Integer> idList);

    String getTypeName(Integer id);
}
