package exam.demo.moduleexam.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.*;
import exam.demo.modulecommon.logging.annotation.MethodEnhancer;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.PageMapUtil;
import exam.demo.modulecommon.utils.RPCUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleexam.manage.PaperApi;
import exam.demo.moduleexam.manage.UserApi;
import exam.demo.moduleexam.pojo.DTO.publish.*;
import exam.demo.moduleexam.pojo.VO.IdAndName;
import exam.demo.moduleexam.pojo.VO.publish.*;
import exam.demo.moduleexam.service.ExamPublishRecordService;
import exam.demo.moduleexam.utils.DateFormatUtil;
import exam.demo.moduleexam.utils.DateToString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("exampublishrecord")
public class ExamPublishRecordController {
    private final static Integer PAGE_SIZE = 8;

    @Autowired
    CommonState state;

    @Autowired
    private ExamPublishRecordService examPublishRecordService;

    @Autowired
    private PaperApi paperApi;

    @Autowired
    private UserApi userApi;

    /**
     * 列出考试发布记录
     *
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "query")
    public CommonResponse<PageVo<ExamPublishRecordTableDataVO>> query(@RequestBody CommonRequest<ExamPublishRecordQueryFormVO> commonRequest) {
        ExamPublishRecordQueryFormVO examPublishRecordQueryFormVO = commonRequest.getData();
        ExamPublishRecordQueryFormDTO examPublishRecordQueryFormDTO = CommonUtils.copyProperties(examPublishRecordQueryFormVO, ExamPublishRecordQueryFormDTO.class);
        examPublishRecordQueryFormDTO.setPublisher(getPublisherId(examPublishRecordQueryFormVO.getPublisher(), commonRequest));
        if (examPublishRecordQueryFormVO.getExamTimeRange() != null && examPublishRecordQueryFormVO.getExamTimeRange().size() != 0) {
            List<String> examTimeRange = DateToString.convert(examPublishRecordQueryFormVO.getExamTimeRange());
            examPublishRecordQueryFormDTO.setExamTimeRange(examTimeRange);
        }
        if (examPublishRecordQueryFormVO.getPublishTimeRange() != null && examPublishRecordQueryFormVO.getPublishTimeRange().size() != 0) {
            List<String> publishTimeRange = DateToString.convert(examPublishRecordQueryFormVO.getPublishTimeRange());
            examPublishRecordQueryFormDTO.setPublishTimeRange(publishTimeRange);
        }
        Page<ExamPublishRecordQueryFormVO> page = new Page<>(examPublishRecordQueryFormVO.getCurrentPage(), PAGE_SIZE);
        List<ExamPublishRecordTableDataDTO> tableDataDTOS = examPublishRecordService.queryExamPublishRecord(examPublishRecordQueryFormDTO);
        List<ExamPublishRecordTableDataVO> tableDataVOS = new ArrayList<>();
        for (ExamPublishRecordTableDataDTO tableDataDTO : tableDataDTOS) {
            ExamPublishRecordTableDataVO tableDataVO = new ExamPublishRecordTableDataVO();
            BeanUtils.copyProperties(tableDataDTO, tableDataVO);
            // publisher读出姓名
            tableDataVO.setPublisher(getPublisherName(tableDataDTO.getPublisher(), commonRequest));
            //TODO 状态转为文本
            if (tableDataDTO.getStatus() != null)
                tableDataVO.setStatus(tableDataDTO.getStatus().toString());
            // get publishTimes
            if (tableDataDTO.getPaperId() != null) {
                CommonRequest<Integer> request = new CommonRequest<>();
                request.setVersion(state.getVersion());
                request.setToken(TokenUtils.getToken());
                request.setData(tableDataDTO.getPaperId());
                // 从试卷服务获取试卷发布次数
                tableDataVO.setPublishTimes(RPCUtils.parseResponse(paperApi.queryPublishedTimesByPaperId(request), Integer.class, RPCUtils.PAPER));
            }
            // get examinersName
            tableDataVO.setExaminers(getExaminersName(tableDataDTO.getExaminers(), commonRequest));
            tableDataVO.setCreatedTime(DateFormatUtil.format(tableDataDTO.getCreatedTime()));
            tableDataVO.setEndTime(DateFormatUtil.format(tableDataDTO.getEndTime()));
            tableDataVOS.add(tableDataVO);
        }
        PageVo<ExamPublishRecordTableDataVO> pageVo = PageMapUtil.getPageMap(tableDataVOS, page);
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, pageVo);
    }

    /**
     * 发布一条考试发布记录
     *
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "save")
    public CommonResponse<Boolean> save(@RequestBody @Valid CommonRequest<ExamPublishRecordPublishFormVO> commonRequest) {
        ExamPublishRecordPublishFormDTO examPublishRecordPublishFormDTO = CommonUtils.copyProperties(commonRequest.getData(), ExamPublishRecordPublishFormDTO.class);
        if (examPublishRecordService.addExamPublishRecord(examPublishRecordPublishFormDTO)) {
            CommonRequest<Integer> paperRequest = new CommonRequest<>();
            paperRequest.setVersion(state.getVersion());
            paperRequest.setData(examPublishRecordPublishFormDTO.getPaperId());
            paperRequest.setToken(TokenUtils.getToken());
            if (paperApi.publishPaper(paperRequest).getCode().equals(state.SUCCESS)) {
                return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
            }
        }
        return new CommonResponse<>(state.FAIL, state.FAIL_MSG, false);
    }

    /**
     * 删除考试发布记录
     *
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "del")
    public CommonResponse<Boolean> del(@RequestBody @Valid CommonRequest<List<ExamPublishRecordDeleteFormVO>> commonRequest) {

        List<ExamPublishRecordDeleteFormDTO> dtoList = CommonUtils.convertList(commonRequest.getData(), ExamPublishRecordDeleteFormDTO.class);
        if (examPublishRecordService.deleteExamPublishRecord(dtoList)) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.FAIL, state.FAIL_MSG, false);
    }


    /**
     * 更新考试发布记录
     *
     * @param commonRequest
     * @return
     */
    @MethodEnhancer
    @RequestMapping(value = "update")
    public CommonResponse<Boolean> update(@RequestBody @Valid CommonRequest<ExamPublishRecordEditFormVO> commonRequest) {
        ExamPublishRecordEditFormDTO dto = CommonUtils.copyProperties(commonRequest.getData(), ExamPublishRecordEditFormDTO.class);
        if (examPublishRecordService.updateExamPublishRecord(dto)) {
            return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, true);
        }
        return new CommonResponse<>(state.FAIL, state.FAIL_MSG, false);
    }

    @MethodEnhancer
    @PostMapping(value = "getpaperinfo")
    public CommonResponse<Collection> getPaperInfo(@RequestBody CommonRequest<String> commonRequest) {
        UserPermission userPermission = TokenUtils.getUser();
        CommonRequest<FuzzySearch> request = new CommonRequest<>();
        FuzzySearch queryPaperInfoDTO = new FuzzySearch();
        queryPaperInfoDTO.setPaperName(commonRequest.getData());
        queryPaperInfoDTO.setCompanyId(userPermission.getCompanyId());
        request.setToken(commonRequest.getToken());
        request.setData(queryPaperInfoDTO);
        request.setVersion(commonRequest.getVersion());
        Collection<PaperIdWithName> list = RPCUtils.parseCollectionTypeResponse(paperApi.fuzzySearchByPaperName(request), PaperIdWithName.class, RPCUtils.PAPER);
        Collection<IdAndName> idAndNames = new ArrayList<>();
        for (PaperIdWithName paperIdWithName : list) {
            idAndNames.add(IdAndName.builder().name(paperIdWithName.getPaperName()).id(paperIdWithName.getId()).build());
        }
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, idAndNames);
    }

    @MethodEnhancer
    @RequestMapping(value = "getuserinfo", method = RequestMethod.POST)
    public CommonResponse<Collection> getUserInfo(@RequestBody CommonRequest<String> commonRequest) {
        UserPermission userPermission = TokenUtils.getUser();
        CommonRequest<UserDto> request = new CommonRequest<>();
        UserDto queryExaminersInfoDTO = new UserDto();
        queryExaminersInfoDTO.setName(commonRequest.getData());
        queryExaminersInfoDTO.setCompanyId(userPermission.getCompanyId());
        request.setVersion(commonRequest.getVersion());
        request.setData(queryExaminersInfoDTO);
        request.setToken(request.getToken());
        return new CommonResponse<>(state.SUCCESS, state.SUCCESS_MSG, RPCUtils.parseCollectionTypeResponse(userApi.queryScoringOfficer(request), UserDto.class, RPCUtils.USER));
    }

    /**
     * 获取阅卷官名字
     *
     * @param ids
     * @param commonRequest
     * @return
     */
    private String getExaminersName(List<Integer> ids, CommonRequest commonRequest) {
        if (ids != null) {
            List<String> names = new ArrayList<>();
            StringBuffer examiners = new StringBuffer();
            //去用户服务获取姓名
            for (Integer id : ids) {
                return getPublisherName(id, commonRequest);
            }
            for (String name : names) {
                examiners.append(name + ";");
            }
            return examiners.toString();
        }
        return null;
    }

    /**
     * 通过id获取userName
     *
     * @param id
     * @param commonRequest
     * @return
     */
    private String getPublisherName(Integer id, CommonRequest commonRequest) {
        if (id != null) {
            CommonRequest<Integer> request = new CommonRequest<>();
            request.setToken(commonRequest.getToken());
            request.setData(id);
            request.setVersion(state.getVersion());
            return RPCUtils.parseResponse(userApi.getUserNameById(request), String.class, RPCUtils.USER);
        }
        return null;
    }

    /**
     * 通过名字获取人物id
     *
     * @param name
     * @param commonRequest
     * @return
     */
    private Integer getPublisherId(String name, CommonRequest commonRequest) {
        if (name != null) {
            CommonRequest<String> request = new CommonRequest<>();
            request.setToken(commonRequest.getToken());
            request.setData(name);
            request.setVersion(state.getVersion());
            return RPCUtils.parseResponse(userApi.getUserIdByName(request), Integer.class, RPCUtils.USER);
        }
        return null;
    }
}
