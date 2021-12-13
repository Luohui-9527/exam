package exam.demo.server.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.server.biz.service.DictionaryService;
import exam.demo.server.constant.ControllerConstant;
import exam.demo.server.dto.DictionaryDto;
import exam.demo.server.exception.BaseInfoError;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.model.Dictionary;
import exam.demo.server.pojo.vo.DictionaryListVo;
import exam.demo.server.pojo.vo.DictionaryQueryVo;
import exam.demo.server.pojo.vo.DictionaryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luohui
 */
@RequestMapping(ControllerConstant.DICTIONARY)
@RestController
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    CommonState state;

    /**
     * @param dictionaryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.SAVE_DICTIONARY)
    public CommonResponse<Boolean> save(@RequestBody DictionaryVo dictionaryVo) {
        DictionaryDto dictionaryDto = CommonUtils.copyProperties(dictionaryVo, DictionaryDto.class);
        dictionaryService.save(dictionaryDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.DELETE_DICTIONARY)
    public CommonResponse<Boolean> delete(@RequestBody List<DictionaryVo> dictionaryVoList) {
        List<Dictionary> dictionaryList = CommonUtils.convertList(dictionaryVoList, Dictionary.class);
        for (Dictionary dictionary : dictionaryList) {
            dictionary.setOrgId(TokenUtils.getUser().getOrgId());
        }
        if (dictionaryService.removeByIds(dictionaryList.stream().map(Dictionary::getId).collect(Collectors.toList()))) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        throw new BaseInfoException(BaseInfoError.DICTIONARY_DEL_FAIL);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.UPDATE_DICTIONARY)
    public CommonResponse<Boolean> update(@RequestBody DictionaryVo dictionaryVo) {
        DictionaryDto dictionaryDto = CommonUtils.copyProperties(dictionaryVo, DictionaryDto.class);
        dictionaryDto.setOldVersion(dictionaryDto.getVersion());
        dictionaryDto.setOrgId(TokenUtils.getUser().getOrgId());
        if (dictionaryService.update(dictionaryDto)) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        throw new BaseInfoException(BaseInfoError.DICTIONARY_UPDATE_FAIL);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_DICTIONARY)
    public CommonResponse<PageVo<DictionaryListVo>> queryDictionary(@RequestBody DictionaryQueryVo vo) {
        Page<DictionaryQueryVo> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        Dictionary dictionary = new Dictionary();
        dictionary.setOrgId(TokenUtils.getUser().getOrgId());
        dictionary.setCategory(vo.getCategory());
        dictionary.setName(vo.getName());
        List<Dictionary> dictionaryList = dictionaryService.queryDictionary(dictionary);
        List<DictionaryListVo> dictionaryVoList = CommonUtils.convertList(dictionaryList, DictionaryListVo.class);
        PageVo<DictionaryListVo> pageVo = PageMapUtil.getPageMap(dictionaryVoList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_DICTIONARY_VALUE)
    public CommonResponse<List> queryDictionaryValue(@RequestBody DictionaryQueryVo queryVo) {
        Dictionary dictionary = new Dictionary();
        dictionary.setOrgId(TokenUtils.getUser().getOrgId());
        dictionary.setName(queryVo.getName());
        dictionary.setCategory(queryVo.getCategory());
        List<Dictionary> dictionaryList = dictionaryService.queryDictionary(dictionary);
        List<DictionaryListVo> res = CommonUtils.convertList(dictionaryList, DictionaryListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, res);
    }
}
