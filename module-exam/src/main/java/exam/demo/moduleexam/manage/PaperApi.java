package exam.demo.moduleexam.manage;


import exam.demo.modulecommon.common.*;
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
    CommonResponse<Boolean> publishPaper(CommonRequest<Long> paperId);

    @GetMapping({"/info/list/paper"})
    CommonResponse<List<PaperIdWithName>> listPaper();

    @PostMapping({"/info/fuzzy/search"})
    CommonResponse<List<PaperIdWithName>> fuzzySearchByPaperName(CommonRequest<FuzzySearch> request);

    @PostMapping({"/info/query/detail"})
    CommonResponse<PaperDetail> queryDetailByPaperId(CommonRequest<Long> request);

    @PostMapping({"/info/query/published/time"})
    CommonResponse<Integer> queryPublishedTimesByPaperId(CommonRequest<Long> request);

    @PostMapping({"/info/query/paper/name"})
    CommonResponse<String> queryPaperNameByPaperId(CommonRequest<Long> request);

    @PostMapping("/info/query/paper/score")
    CommonResponse queryPaperScore(CommonRequest<Long> request);
}
