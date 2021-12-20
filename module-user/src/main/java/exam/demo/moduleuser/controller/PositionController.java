package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.moduleuser.constant.ControllerConstants;
import exam.demo.moduleuser.dto.PositionDto;
import exam.demo.moduleuser.pojo.model.Position;
import exam.demo.moduleuser.pojo.vo.PositionItemVo;
import exam.demo.moduleuser.pojo.vo.PositionListVo;
import exam.demo.moduleuser.pojo.vo.PositionQueryVo;
import exam.demo.moduleuser.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-07
 */
@RestController
@RequestMapping(ControllerConstants.POSITION)
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true", methods = {})
public class PositionController {
    @Autowired
    IPositionService positionService;

    @Autowired
    CommonState state;


    @MethodEnhancer
    @PostMapping(ControllerConstants.SAVE_P)
    public CommonResponse<Boolean> savePosition(@RequestBody @Valid CommonRequest<PositionItemVo> request) {
        PositionDto positionDto = CommonUtils.copyProperties(request.getData(), PositionDto.class);
        positionService.save(positionDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PutMapping(ControllerConstants.UPDATE_P)
    public CommonResponse<Boolean> updatePosition(@RequestBody @Valid CommonRequest<PositionItemVo> request) {
        PositionDto positionDto = CommonUtils.copyProperties(request.getData(), PositionDto.class);
        positionDto.setOldVersion(positionDto.getVersion());
        positionService.update(positionDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @DeleteMapping(ControllerConstants.DEL_P)
    public CommonResponse<Boolean> deletePosition(@RequestBody @Valid CommonRequest<List<PositionItemVo>> request) {
        List<Position> positionList = CommonUtils.convertList(request.getData(), Position.class);
        positionService.delete(positionList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.GET_UF_P)
    public CommonResponse<PositionListVo> getUpdateFormPosition(@RequestBody @Valid CommonRequest<Long> request) {
        Position position = positionService.getById(request.getData());
        PositionListVo positionListVo = CommonUtils.copyProperties(position, PositionListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, positionListVo);
    }

    @MethodEnhancer
    @PostMapping(ControllerConstants.QUERY_P)
    public CommonResponse<PageVo<PositionListVo>> queryPosition(@RequestBody @Valid CommonRequest<PositionQueryVo> request) {
        Position position = CommonUtils.copyProperties(request.getData(), Position.class);
        position.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<PositionListVo> page = new Page<>(request.getData().getCurrentPage(), request.getData().getTotalPages());
        List<Position> positionList = positionService.list(position);
        List<PositionListVo> listVos = CommonUtils.convertList(positionList, PositionListVo.class);
        PageVo<PositionListVo> pageVo = PageMapUtil.getPageMap(listVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping(ControllerConstants.QUERY_OPTIONS_P)
    public CommonResponse<List> queryCompOptionsPosition() {
        List<Position> positionList = positionService.listCompany();
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, CommonUtils.convertList(positionList, PositionListVo.class));
    }
}
