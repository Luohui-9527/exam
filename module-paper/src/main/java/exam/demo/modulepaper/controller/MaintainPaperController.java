package exam.demo.modulepaper.controller;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PaperDetail;

import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.feign.BaseInfoFeign;
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
@RequestMapping("/maintain")
@RestController
public class MaintainPaperController {

    @Autowired
    IPaperService paperService;

    @Autowired
    CommonState commonState;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    BaseInfoFeign baseInfoFeign;

    @Autowired
    BaseService baseService;

    /**
     * 查询试卷表单
     *
     * @param paperQueryVo
     * @return
     */
    @MethodEnhancer
    @PostMapping("/query")
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
    @PostMapping("/update")
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
    @PostMapping("/delete")
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
    @PostMapping("/detail")
    public CommonResponse<PaperDetail> paperDetail(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, baseService.queryDetailByPaperId(paperId));
    }

    @MethodEnhancer
    @PostMapping("/publish")
    public CommonResponse<Boolean> publishPaper(@RequestParam("paperId") Long paperId) {
        boolean result = paperService.publish(paperId);
        return new CommonResponse<>(commonState.getVersion(), commonState.SUCCESS, commonState.SUCCESS_MSG, result);
    }

}
