package exam.demo.moduleuser.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.common.PageVo;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
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
@RequestMapping("/position")
@CrossOrigin(allowedHeaders = "*", allowCredentials = "false", methods = {})
public class PositionController {
    @Autowired
    IPositionService positionService;

    @Autowired
    CommonState state;


    @MethodEnhancer
    @PostMapping("/save")
    public CommonResponse<Boolean> savePosition(@RequestBody @Valid PositionItemVo request) {
        PositionDto positionDto = CommonUtils.copyProperties(request, PositionDto.class);
        positionService.save(positionDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/update")
    public CommonResponse<Boolean> updatePosition(@RequestBody @Valid PositionItemVo request) {
        PositionDto positionDto = CommonUtils.copyProperties(request, PositionDto.class);
        positionDto.setOldVersion(positionDto.getVersion());
        positionService.update(positionDto);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/delete")
    public CommonResponse<Boolean> deletePosition(@RequestBody @Valid List<PositionItemVo> request) {
        List<Position> positionList = CommonUtils.convertList(request, Position.class);
        positionService.delete(positionList);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
    }

    @MethodEnhancer
    @PostMapping("/form")
    public CommonResponse<PositionListVo> getUpdateFormPosition(@RequestBody @Valid Long request) {
        Position position = positionService.getById(request);
        PositionListVo positionListVo = CommonUtils.copyProperties(position, PositionListVo.class);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, positionListVo);
    }

    @MethodEnhancer
    @PostMapping("/query")
    public CommonResponse<PageVo<PositionListVo>> queryPosition(@RequestBody @Valid PositionQueryVo request) {
        Position position = CommonUtils.copyProperties(request, Position.class);
        position.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        Page<PositionListVo> page = new Page<>(request.getCurrentPage(), request.getTotalPages());
        List<Position> positionList = positionService.list(position);
        List<PositionListVo> listVos = CommonUtils.convertList(positionList, PositionListVo.class);
        PageVo<PositionListVo> pageVo = PageMapUtil.getPageMap(listVos, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    @MethodEnhancer
    @GetMapping("/options")
    public CommonResponse<List> queryCompOptionsPosition() {
        List<Position> positionList = positionService.listCompany();
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, CommonUtils.convertList(positionList, PositionListVo.class));
    }
}
