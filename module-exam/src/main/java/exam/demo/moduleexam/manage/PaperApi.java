package exam.demo.moduleexam.manage;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.FuzzySearch;
import exam.demo.modulecommon.common.PaperDetail;
import exam.demo.modulecommon.common.PaperIdWithName;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
@FeignClient(ApiConstant.SERVICE_NAME_PAPER)
public interface PaperApi {
    @PostMapping({"/info/publish/paper"})
    CommonResponse<Boolean> publishPaper(Integer paperId);

    @GetMapping({"/info/list/paper"})
    CommonResponse<List<PaperIdWithName>> listPaper();

    @PostMapping({"/info/fuzzy/search"})
    CommonResponse<List<PaperIdWithName>> fuzzySearchByPaperName(FuzzySearch request);

    @PostMapping({"/info/query/detail"})
    CommonResponse<PaperDetail> queryDetailByPaperId(Integer request);

    @PostMapping({"/info/query/published/time"})
    CommonResponse<Integer> queryPublishedTimesByPaperId(Integer request);

    @PostMapping({"/info/query/paper/name"})
    CommonResponse<String> queryPaperNameByPaperId(Integer request);

    @PostMapping("/info/query/paper/score")
    CommonResponse queryPaperScore(Integer request);
}
