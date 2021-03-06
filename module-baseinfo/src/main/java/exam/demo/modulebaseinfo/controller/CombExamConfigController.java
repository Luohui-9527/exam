package exam.demo.modulebaseinfo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulebaseinfo.constant.ControllerConstant;
import exam.demo.modulebaseinfo.constant.EnumUserInfoType;
import exam.demo.modulebaseinfo.dto.CombExamConfigDto;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfig;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import exam.demo.modulebaseinfo.pojo.vo.CombExamConfigItemListVo;
import exam.demo.modulebaseinfo.pojo.vo.CombExamConfigListVo;
import exam.demo.modulebaseinfo.pojo.vo.CombExamConfigQueryVo;
import exam.demo.modulebaseinfo.pojo.vo.CombExamConfigVo;
import exam.demo.modulebaseinfo.service.CombExamConfigItemService;
import exam.demo.modulebaseinfo.service.CombExamConfigService;
import exam.demo.modulebaseinfo.service.impl.BaseService;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luohui
 */
@RequestMapping(ControllerConstant.COMB_EXAM_CONFIG)
@RestController
public class CombExamConfigController {
    @Autowired
    CombExamConfigService combExamConfigService;

    @Autowired
    CombExamConfigItemService combExamConfigItemService;

    @Autowired
    CommonState state;

    @Autowired
    BaseService baseService;

    @MethodEnhancer
    @PostMapping(ControllerConstant.SAVE_COMB_EXAM_CONFIG)
    public CommonResponse<Boolean> save(@RequestBody CombExamConfigVo combExamConfigVo) {
        CombExamConfigDto dto = CommonUtils.copyProperties(combExamConfigVo, CombExamConfigDto.class);
        dto.setCombExamConfigItemDtoList(CommonUtils.convertList(combExamConfigVo.getCombExamConfigItemVOs(), CombExamConfigItemDto.class));
        combExamConfigService.saveCombExamConfigItem(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.DELETE_CEC)
    public CommonResponse<Boolean> delete(@RequestBody List<CombExamConfigVo> voList) {
        List<CombExamConfigDto> dtoList = CommonUtils.convertList(voList, CombExamConfigDto.class);
        for (CombExamConfigDto dto : dtoList) {
            dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        combExamConfigService.deleteCombExamConfig(dtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.UPDATE_CEC)
    public CommonResponse<Boolean> update(@RequestBody CombExamConfigVo combExamConfigVo) {
        CombExamConfigDto dto = CommonUtils.copyProperties(combExamConfigVo, CombExamConfigDto.class);
        dto.setOldVersion(dto.getVersion());
        dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        dto.setCombExamConfigItemDtoList(CommonUtils.convertList(combExamConfigVo.getCombExamConfigItemVOs(), CombExamConfigItemDto.class));
        combExamConfigService.updateCombExamConfig(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_CONFIG)
    public CommonResponse<PageVo<CombExamConfigListVo>> queryCombExamConfig(@RequestBody CombExamConfigQueryVo queryVo) {
        Page<CombExamConfigQueryVo> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        List<CombExamConfig> configList = combExamConfigService.listById(CommonUtils.copyProperties(queryVo, CombExamConfig.class));
        List<CombExamConfigListVo> voList = CommonUtils.convertList(configList, CombExamConfigListVo.class);
        for (CombExamConfigListVo listVo : voList) {
            if (listVo.getCompanyId() != null) {
                listVo.setCompany(baseService.getUserInfo(listVo.getCompanyId(), EnumUserInfoType.COMPANY));
            }
            if (listVo.getUpdatedBy() != null) {
                listVo.setUpdatedByName(baseService.getUserInfo(listVo.getUpdatedBy(), EnumUserInfoType.USER));
            }
        }
        PageVo<CombExamConfigListVo> pageVo = PageMapUtil.getPageMap(voList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.QUERY_CONFIG_ITEM)
    public CommonResponse<PageVo<CombExamConfigItemListVo>> queryCombExamConfigItem(@RequestBody CombExamConfigQueryVo queryVo) {
        Page<CombExamConfigQueryVo> page = new Page<>(queryVo.getPageNum(), queryVo.getPageSize());
        CombExamConfigDetail item = CommonUtils.copyProperties(queryVo, CombExamConfigDetail.class);
        item.setCombExamConfigId(queryVo.getId());
        List<CombExamConfigDetail> combExamConfigDetailList = combExamConfigItemService.listByCombExamId(item);
        List<CombExamConfigItemListVo> voList = CommonUtils.convertList(combExamConfigDetailList, CombExamConfigItemListVo.class);
        PageVo<CombExamConfigItemListVo> pageVo = PageMapUtil.getPageMap(voList, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }
}
