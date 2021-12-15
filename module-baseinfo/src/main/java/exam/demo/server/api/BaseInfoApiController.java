package exam.demo.server.api;


import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.server.biz.service.*;
import exam.demo.server.constant.ApiConstant;
import exam.demo.server.constant.EnumInfoType;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.model.CombExamConfigDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@RestController
public class BaseInfoApiController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CommonState state;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    CombExamConfigService combExamConfigService;

    @Autowired
    CombExamConfigItemService combExamConfigItemService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    SubjectTypeService subjectTypeService;

    /**
     * 根据CategoryID获取Category值
     *
     * @param baseDataDto
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.LIST_CATEGORY)
    public CommonResponse<BaseDataDto> listCategory(@RequestBody @Valid BaseDataDto baseDataDto) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, getBaseInfo(baseDataDto, EnumInfoType.CATEGORY));
    }

    private Map<Integer, String> resolve(List<Integer> idList, List<String> valueList, Map<Integer, String> map) {
        for (int i = 0; i < idList.size(); i++) {
            map.put(idList.get(i), valueList.get(i));
        }
        return map;
    }

    /**
     * 获取字典值
     *
     * @param baseDataDto
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.GET_BASE_DATAS)
    public CommonResponse<BaseDataDto> getBaseDataS(@RequestBody @Valid BaseDataDto baseDataDto) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, getBaseInfo(baseDataDto, EnumInfoType.DICTIONARY));
    }

    /**
     * 通过Id获取对应字典值
     *
     * @param dictionaryId
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.GET_BASE_DATA)
    public CommonResponse<String> getBaseData(@RequestBody @Valid Integer dictionaryId) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, dictionaryService.getDictionaryValue(dictionaryId));
    }

    /**
     * 根据组卷配置获取试卷
     *
     * @param combExamConfigId
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.GET_SUBJECT_AND_ANSWER)
    public CommonResponse<SubjectPackage> getSubjectAndAnswer(@RequestParam("combExamConfigId") Integer combExamConfigId) {
        CombExamConfigDetail config = new CombExamConfigDetail();
        config.setCombExamConfigId(combExamConfigId);
        List<CombExamConfigDetail> itemList = combExamConfigItemService.listByCombExamId(config);
        SubjectPackage subjectPackage = subjectService.getSubject(itemList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, subjectPackage);
    }

    /**
     * 根据用户在线选择的组卷配置明细进行选择试题
     *
     * @param combExamConfigItemDtoList
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.GET_SUBJECT_CUSTOMIZED)
    public CommonResponse<SubjectPackage> getSubjectAndAnswerCustomized(@RequestBody @Valid List<CombExamConfigItemDto> combExamConfigItemDtoList) {
        List<CombExamConfigDetail> combExamConfigDetailList = CommonUtils.convertList(combExamConfigItemDtoList, CombExamConfigDetail.class);
        SubjectPackage subjectPackage = subjectService.getSubject(combExamConfigDetailList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, subjectPackage);
    }

    /**
     * 通过试题Id来获取题目和答案
     *
     * @param subjectIdList
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.GET_SUBJECT_BY_ID)
    public CommonResponse<SubjectPackage> getSubjectById(@RequestBody @Valid List<Integer> subjectIdList) {
        SubjectPackage subjectPackage = subjectService.getSubjectById(subjectIdList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, subjectPackage);
    }

    /**
     * 获取题目类型
     *
     * @param baseDataDto
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.LIST_SUBJECT_TYPE)
    public CommonResponse<BaseDataDto> getSubjectType(@RequestBody @Valid BaseDataDto baseDataDto) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, getBaseInfo(baseDataDto, EnumInfoType.SUBJECT_TYPE));
    }

    private BaseDataDto getBaseInfo(BaseDataDto baseDataDto, EnumInfoType type) {
        List<Integer> idList = new ArrayList<>(baseDataDto.getBaseInfoMap().keySet());
        List<String> values;
        switch (type) {
            case CATEGORY:
                values = categoryService.getCategoryName(idList);
                break;
            case DICTIONARY:
                values = dictionaryService.getDictionary(idList);
                break;
            case SUBJECT_TYPE:
                values = subjectTypeService.getTypeName(idList);
                break;
            default:
                throw new BaseInfoException(StarterError.SYSTEM_PARAMETER_VALUE_INVALID);
        }
        Map<Integer, String> map = resolve(idList, values, baseDataDto.getBaseInfoMap());
        baseDataDto.setBaseInfoMap(map);
        return baseDataDto;
    }

    @PostMapping(ApiConstant.GET_CATEGORY_VAL)
    public CommonResponse<String> getCategory(@RequestBody Integer id) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, categoryService.getCategoryNameById(id));
    }

    @PostMapping(ApiConstant.GET_SUBJECT_TYPE)
    public CommonResponse<String> getSubjectType(@RequestBody Integer id) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, subjectTypeService.getTypeName(id));
    }
}
