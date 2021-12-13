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
import exam.demo.modulepaper.exception.PaperException;
import exam.demo.modulepaper.pojo.dto.PaperDto;
import exam.demo.modulepaper.pojo.vo.CombExamConfigVo;
import exam.demo.modulepaper.pojo.vo.CustomizedCombExamConfigVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-03
 */
@Slf4j
@RestController
@RequestMapping(ControllerConstant.CREATE)
public class CreatePaperController {
    @Autowired
    CommonState state;

    @Autowired
    PaperService paperService;


    /**
     * 快速组卷，应用场景：前端选择组卷配置后，将组卷配置
     *
     * @param vo
     * @return
     */
    @MethodEnhancer
    @PostMapping(ControllerConstant.CREATE_FAST_GEN)
    public CommonResponse<String> fastGen(@RequestBody CombExamConfigVo vo) {
        UserPermission userPermission = checkAccessAuthority();
        PaperDto paperDto = CommonUtils.copyProperties(vo, PaperDto.class);
        paperDto.setConfigId(vo.getId());
        paperDto.setDescription(vo.getRemark());
        paperDto.setPaperCreator(userPermission.getUserName());
        paperDto.setStatus((byte) 1);
        boolean res = paperService.generateFastMode(paperDto);
        if (res) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, state.SUCCESS_MSG);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL, state.FAIL_MSG);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstant.CREATE_STANDARD_GEN)
    public CommonResponse standardGen(@RequestBody CustomizedCombExamConfigVo vo) {
        UserPermission userPermission = checkAccessAuthority();
        PaperDto paperDto = new PaperDto();
        paperDto.setPaperCreator(userPermission.getUserName());
        boolean res = paperService.generateNormalMode(paperDto, vo);
        if (res) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, state.SUCCESS_MSG);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL, state.FAIL_MSG);
    }


    @MethodEnhancer
    @PostMapping(ControllerConstant.CREATE_TEMPLATE_GEN)
    public CommonResponse templateGen(@RequestParam("templateId") Long templateId) {
        checkAccessAuthority();
        PaperDto paperDto = new PaperDto();
        paperDto.setId(templateId);
        boolean res = paperService.generateTemplateMode(paperDto);
        if (res) {
            return new CommonResponse<>(state.getVersion(), state.SUCCESS, state.SUCCESS_MSG, state.SUCCESS_MSG);
        }
        return new CommonResponse<>(state.getVersion(), state.FAIL, state.FAIL, state.FAIL_MSG);
    }

    private UserPermission checkAccessAuthority() {
        UserPermission userPermission = TokenUtils.getUser();
        if (userPermission.getCompanyId() == null) {
            throw new PaperException(StarterError.SYSTEM_ACCESS_INVALID);
        }
        return userPermission;
    }

    @GetMapping("/he")
    public String test() {
        return "Hello";
    }
}
