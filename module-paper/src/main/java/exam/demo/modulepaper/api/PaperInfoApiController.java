package exam.demo.modulepaper.api;


import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.modulepaper.pojo.vo.PaperQueryVo;
import exam.demo.modulepaper.service.IPaperService;
import exam.demo.modulepaper.service.impl.BaseService;
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
@RequestMapping("/paper")
public class PaperInfoApiController {


    @Autowired
    IPaperService paperService;

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
    @PostMapping("/publish")
    public CommonResponse<Boolean> publishPaper(@RequestBody Long paperId) {
        if (paperService.publish(paperId)) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL, false);
    }

    /**
     * 列出试卷列表
     *
     * @param queryVo 分页查询参数
     * @return
     */
    @MethodEnhancer
    @PostMapping("/list")
    public CommonResponse<PageVo<PaperListVo>> listPaper(@RequestBody PaperQueryVo queryVo) {
        UserPermission userPermission = TokenUtils.getUser();
        PageVo<PaperListVo> res = paperService.listVo(userPermission.getCompanyId(), queryVo);
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, res);
    }

    /**
     * 通过试卷名称进行模糊搜索
     *
     * @param fuzzySearch
     * @return
     */
    @MethodEnhancer
    @PostMapping("/search")
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
    @GetMapping("/detail")
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
    @GetMapping("/publishedTimes")
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
    @GetMapping("/name")
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
    @GetMapping("/score")
    public CommonResponse<Double> queryPaperScore(@RequestParam("paperId") Long paperId) {
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, paperService.getScore(paperId));
    }
}
