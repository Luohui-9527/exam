package exam.demo.modulebaseinfo.controller;

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
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luohui
 */
@RequestMapping("/combExamConfig")
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
    @PostMapping("/save")
    public CommonResponse<Boolean> save(@RequestBody CombExamConfigVo combExamConfigVo) {
        CombExamConfigDto dto = CommonUtils.copyProperties(combExamConfigVo, CombExamConfigDto.class);
        dto.setCombExamConfigItemDtoList(CommonUtils.convertList(combExamConfigVo.getCombExamConfigItemVOs(), CombExamConfigItemDto.class));
        combExamConfigService.saveCombExamConfigItem(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> delete(@RequestBody List<CombExamConfigVo> voList) {
        List<CombExamConfigDto> dtoList = CommonUtils.convertList(voList, CombExamConfigDto.class);
        for (CombExamConfigDto dto : dtoList) {
            dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        }
        combExamConfigService.deleteCombExamConfig(dtoList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping("/update")
    public CommonResponse<Boolean> update(@RequestBody CombExamConfigVo combExamConfigVo) {
        CombExamConfigDto dto = CommonUtils.copyProperties(combExamConfigVo, CombExamConfigDto.class);
        dto.setOldVersion(dto.getVersion());
        dto.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        dto.setCombExamConfigItemDtoList(CommonUtils.convertList(combExamConfigVo.getCombExamConfigItemVOs(), CombExamConfigItemDto.class));
        combExamConfigService.updateCombExamConfig(dto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }


    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<List<CombExamConfigListVo>> queryCombExamConfig(@RequestBody CombExamConfigQueryVo queryVo) {
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
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, voList);
    }

    @MethodEnhancer
    @PostMapping("/queryItem")
    public CommonResponse<List<CombExamConfigItemListVo>> queryCombExamConfigItem(@RequestBody CombExamConfigQueryVo queryVo) {
        CombExamConfigDetail item = new CombExamConfigDetail();
        item.setCombExamConfigId(queryVo.getId());
        List<CombExamConfigDetail> combExamConfigDetailList = combExamConfigItemService.listByCombExamId(item);
        List<CombExamConfigItemListVo> voList = CommonUtils.convertList(combExamConfigDetailList, CombExamConfigItemListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, voList);
    }
}
