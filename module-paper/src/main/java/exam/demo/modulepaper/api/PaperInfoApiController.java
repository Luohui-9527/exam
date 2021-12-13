package exam.demo.modulepaper.api;


import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.constant.ApiConstant;
import exam.demo.modulecommon.constant.ControllerConstant;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.modulepaper.biz.service.PaperService;
import exam.demo.modulepaper.biz.service.impl.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@RestController
@Slf4j
@RequestMapping(ControllerConstant.API)
public class PaperInfoApiController {


    @Autowired
    PaperService paperService;

    @Autowired
    CommonState state;

    @Autowired
    CacheManager cacheManager;

    @Autowired
    BaseService baseService;

    /**
     * 发布试卷
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.PAPER_INFO_PUBLISH_PAPER)
    public CommonResponse<Boolean> publishPaper(@RequestBody Long paperId) {
        if (paperService.publish(paperId)) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL, false);
    }

    /**
     * 列出试卷Id和名称
     *
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.PAPER_INFO_LIST_PAPER)
    public CommonResponse<List<PaperIdWithName>> listPaper() {
        UserPermission userPermission = TokenUtils.getUser();
        List<PaperIdWithName> res = paperService.list(userPermission.getCompanyId());
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, res);
    }

    /**
     * 通过试卷名称进行模糊搜索
     *
     * @param fuzzySearch
     * @return
     */
    @MethodEnhancer
    @PostMapping(ApiConstant.PAPER_INFO_FUZZY_SEARCH)
    public CommonResponse<List<PaperIdWithName>> fuzzySearchByPaperName(@RequestBody FuzzySearch fuzzySearch) {
        List<PaperIdWithName> res = paperService.listByName(fuzzySearch);
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, res);
    }

    /**
     * 通过试卷Id查询试卷详情
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.PAPER_INFO_QUERY_DETAIL)
    public CommonResponse<PaperDetail> queryDetailByPaperId(@RequestParam("paperId") Long paperId) {
        PaperDetail detail = baseService.queryDetailByPaperId(paperId);
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, detail);
    }


    /**
     * 通过试卷id查看试卷发布次数
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.PAPER_INFO_QUERY_PUBLISHED_TIME)
    public CommonResponse<Integer> queryPublishedTimesByPaperId(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, paperService.getPaper(paperId).getPublishTimes());
    }

    /**
     * 获取试卷名称
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.PAPER_INFO_QUERY_PAPER_NAME)
    public CommonResponse<String> queryPaperNameByPaperId(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, paperService.getPaper(paperId).getName());
    }

    /**
     * 查询试卷分数
     *
     * @param paperId
     * @return
     */
    @MethodEnhancer
    @GetMapping(ApiConstant.PAPER_INFO_QUERY_PAPER_SCORE)
    public CommonResponse<Double> queryPaperScore(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, paperService.getScore(paperId));
    }
}
