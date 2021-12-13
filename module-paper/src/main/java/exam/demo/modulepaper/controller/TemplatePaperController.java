package exam.demo.modulepaper.controller;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.constant.ControllerConstant;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.modulepaper.biz.service.PaperService;
import exam.demo.modulepaper.biz.service.impl.BaseService;
import exam.demo.modulepaper.exception.PaperException;
import exam.demo.modulepaper.pojo.dto.PaperDto;
import exam.demo.modulepaper.pojo.dto.PaperQueryDto;
import exam.demo.modulepaper.pojo.vo.PaperQueryVo;
import exam.demo.modulepaper.pojo.vo.PaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */
@RequestMapping(ControllerConstant.TEMPLATE)
@RestController
public class TemplatePaperController {

    @Autowired
    PaperService paperService;

    @Autowired
    CommonState state;

    @Autowired
    BaseService baseService;

    /**
     * 根据模板下载试卷
     *
     * @param paperVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.TEMPLATE_DOWNLOAD_PAPER)
    public CommonResponse<Boolean> downloadPaper(@RequestBody PaperVo paperVo) {
        checkCompany();
        PaperDto paper = CommonUtils.copyProperties(paperVo, PaperDto.class);
        paper.setPreId(paper.getId());
        if (paperService.downloadTemplate(paper)) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL_MSG, false);
    }

    /**
     * 上传模板
     *
     * @param paperVo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.TEMPLATE_UPLOAD_PAPER)
    public CommonResponse<Boolean> uploadPaper(@RequestBody PaperVo paperVo) {
        checkCompany();
        PaperDto paper = CommonUtils.copyProperties(paperVo, PaperDto.class);
        if (paperService.uploadTemplate(paper)) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL_MSG, false);
    }

    /**
     * 删除模板
     *
     * @param templateIdList
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.TEMPLATE_DELETE_PAPER)
    public CommonResponse deleteTemplate(@RequestBody List<Long> templateIdList) {
        if (paperService.deleteTemplate(templateIdList)) {
            baseService.evictPaper(templateIdList);
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL_MSG, false);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.TEMPLATE_QUERY)
    public CommonResponse<Map> queryTemplate(@RequestBody PaperQueryVo paperQueryVo) {
        Map map = paperService.queryTemplate(CommonUtils.copyProperties(paperQueryVo, PaperQueryDto.class));
        return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, map);
    }

    private void checkCompany() {
        UserPermission userPermission = TokenUtils.getUser();
        if (userPermission.getCompanyId() == null) {
            throw new PaperException(StarterError.SYSTEM_ACCESS_INVALID);
        }
    }
}
