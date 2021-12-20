package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.common.BaseDataDto;
import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CommonState;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleuser.dto.SystemParamDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.manage.BaseInfoApi;
import exam.demo.moduleuser.mapper.SystemParamMapper;
import exam.demo.moduleuser.pojo.model.SystemParam;
import exam.demo.moduleuser.pojo.model.TreeList;
import exam.demo.moduleuser.pojo.vo.SystemParamListVo;
import exam.demo.moduleuser.service.ISystemParamService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统配置表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class SystemParamServiceImpl extends ServiceImpl<SystemParamMapper, SystemParam> implements ISystemParamService {
    @Autowired
    CommonState state;

    @Resource
    BaseInfoApi baseInfoApi;


    @FullCommonFieldU
    @Override
    public boolean save(SystemParamDto systemParamDto) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDto, SystemParam.class);
        if (save(systemParam)) {
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<SystemParamDto> systemParamDto) {
        List<SystemParam> systemParamList = CommonUtils.convertList(systemParamDto, SystemParam.class);
        if (baseMapper.deleteByIdList(systemParamList) == systemParamDto.size()) {
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }


    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(SystemParamDto systemParamDto) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDto, SystemParam.class);
        QueryWrapper<SystemParam> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.VERSION, systemParam.getOldVersion());
        if (baseMapper.update(systemParam, wrapper) == 1) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    /**
     * 根据请求条件查询符合条件的参数记录集合
     *
     * @param systemParamDTO 请求条件查询信息
     * @return
     */
    @Override
    public List<SystemParamListVo> queryByCondition(SystemParamDto systemParamDTO) {
        SystemParam systemParam = CommonUtils.copyProperties(systemParamDTO, SystemParam.class);
        QueryWrapper<SystemParam> wrapper = new QueryWrapper<>();
        wrapper.likeRight(StringUtils.isNotEmpty(systemParam.getParam()), MagicPointConstant.PARAM, systemParam.getParam());
        wrapper.eq(systemParam.getParamType() != null, MagicPointConstant.PARAM_TYPE, systemParam.getParamType());
        List<SystemParam> systemParamList = this.list(wrapper);
        if (CommonUtils.isEmpty(systemParamList)) {
            throw new UserException(UserError.DATA_NOT_EXIST);
        }
        List<SystemParamListVo> listVoList = CommonUtils.convertList(systemParamList, SystemParamListVo.class);
        // 从基础数据服务获取值
        Map<Long, String> map = new HashMap<>(systemParamList.size());
        for (SystemParam param : systemParamList) {
            map.put(param.getParamType(), null);
        }
        BaseDataDto baseDataDto = new BaseDataDto(map);
        CommonRequest<BaseDataDto> request = new CommonRequest<>(state.getVersion(), TokenUtils.getToken(), baseDataDto);
        CommonResponse<BaseDataDto> response;
        try {
            response = baseInfoApi.getBaseDataS(request);
        } catch (Exception e) {
            throw new StarterException(StarterError.SYSTEM_RPC_ERROR);
        }
        map = response.getData().getBaseInfoMap();
        for (SystemParamListVo vo : listVoList) {
            vo.setParamTypeName(map.get(vo.getParamType()));
        }
        return listVoList;
    }

    /**
     * 获取参数树集合
     *
     * @return 以树（treelist）形式返回数据
     */
    @Override
    public List<TreeListDto> getQueryListData() {
        List<TreeList> treeListList = baseMapper.getQueryListData();
        return CommonUtils.convertList(treeListList, TreeListDto.class);
    }
}
