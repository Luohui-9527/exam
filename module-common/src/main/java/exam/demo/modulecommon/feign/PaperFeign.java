package exam.demo.modulecommon.feign;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.FuzzySearch;
import exam.demo.modulecommon.common.PaperDetail;
import exam.demo.modulecommon.common.PaperIdWithName;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 试卷服务 Feign 客户端
 * 用于内部调用 paper 服务
 *
 * @author luohui
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_PAPER, path = "/paper")
public interface PaperFeign {
    @PostMapping({"/info/publish/paper"})
    CommonResponse<Boolean> publishPaper(@RequestBody Long paperId);

    @GetMapping({"/info/list/paper"})
    CommonResponse<List<PaperIdWithName>> listPaper();

    @PostMapping({"/info/fuzzy/search"})
    CommonResponse<List<PaperIdWithName>> fuzzySearchByPaperName(@RequestBody FuzzySearch request);

    @PostMapping({"/info/query/detail"})
    CommonResponse<PaperDetail> queryDetailByPaperId(@RequestBody Long request);

    @PostMapping({"/info/query/published/time"})
    CommonResponse<Long> queryPublishedTimesByPaperId(@RequestBody Long request);

    @PostMapping({"/info/query/paper/name"})
    CommonResponse<String> queryPaperNameByPaperId(@RequestBody Long request);

    @PostMapping("/info/query/paper/score")
    CommonResponse queryPaperScore(@RequestBody Long request);
}
