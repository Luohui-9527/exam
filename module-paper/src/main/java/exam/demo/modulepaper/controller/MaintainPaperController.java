package exam.demo.modulepaper.controller;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PaperDetail;
import exam.demo.modulecommon.constant.ControllerConstant;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulepaper.manager.baseinfo.BaseInfoApi;
import exam.demo.modulepaper.pojo.dto.ModifyPaperDto;
import exam.demo.modulepaper.pojo.dto.ModifyPaperSubjectDto;
import exam.demo.modulepaper.pojo.dto.PaperQueryDto;
import exam.demo.modulepaper.pojo.vo.ModifyPaperVo;
import exam.demo.modulepaper.pojo.vo.PaperQueryVo;
import exam.demo.modulepaper.service.IPaperService;
import exam.demo.modulepaper.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@RequestMapping(ControllerConstant.MAINTAIN)
@RestController
public class MaintainPaperController {

    @Autowired
    IPaperService paperService;

    @Autowired
    CommonState commonState;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    BaseInfoApi baseInfoApi;

    @Autowired
    BaseService baseService;

    /**
     * 查询试卷表单
     *
     * @param paperQueryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.MAINTAIN_QUERY_PAPER)
    public CommonResponse<Map> queryPaper(@RequestBody PaperQueryVo paperQueryVo) {
        Map res = paperService.queryPaper(CommonUtils.copyProperties(paperQueryVo, PaperQueryDto.class), false);
        return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, res);
    }

    /**
     * 修改试卷
     *
     * @param modifyPaperVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.MAINTAIN_MODIFY_PAPER)
    public CommonResponse modifyPaper(@RequestBody ModifyPaperVo modifyPaperVo) {
        baseService.published(modifyPaperVo.getId());
        ModifyPaperDto detail = CommonUtils.copyProperties(modifyPaperVo, ModifyPaperDto.class);
        List<ModifyPaperSubjectDto> currentPaperSubjectList = modifyPaperVo.getCurrentPaperSubjectVOList().stream().map(
                subject -> CommonUtils.copyComplicateObject(subject, ModifyPaperSubjectDto.class)
        ).collect(Collectors.toList());
        detail.setCurrentPaperSubjectDtoList(currentPaperSubjectList);
        baseService.evictPaper(modifyPaperVo.getId());
        if (paperService.paperModify(detail)) {
            return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(commonState.getVersion(), commonState.FAIL, commonState.FAIL_MSG, false);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.MAINTAIN_DELETE_PAPER)
    public CommonResponse deletePaper(@RequestBody List<Long> paperIds) {
        if (paperService.paperDelete(paperIds)) {
            baseService.evictPaper(paperIds);
            return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(commonState.getVersion(), commonState.FAIL, commonState.FAIL_MSG, false);
    }

    /**
     * todo 增加分类功能，将一类题目放一起
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.MAINTAIN_PAPER_DETAIL)
    public CommonResponse<PaperDetail> paperDetail(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, baseService.queryDetailByPaperId(paperId));
    }

}
