package exam.demo.modulebaseinfo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulebaseinfo.dao.CombExamConfigDao;
import exam.demo.modulebaseinfo.dto.CombExamConfigDto;
import exam.demo.modulebaseinfo.exception.BaseInfoError;
import exam.demo.modulebaseinfo.exception.BaseInfoException;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfig;
import exam.demo.modulebaseinfo.pojo.model.CombExamConfigDetail;
import exam.demo.modulebaseinfo.service.CombExamConfigItemService;
import exam.demo.modulebaseinfo.service.CombExamConfigService;
import exam.demo.modulebaseinfo.service.SubjectService;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 */
@Service
public class CombExamConfigServiceImpl extends ServiceImpl<CombExamConfigDao, CombExamConfig> implements CombExamConfigService {
    @Autowired
    SnowFlake snowFlake;

    @Autowired
    CombExamConfigItemService itemService;

    @Autowired
    SubjectService subjectService;

    /**
     * 保存组卷配置
     *
     * @param combExamConfigDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @FullCommonField
    @Override
    public boolean saveCombExamConfigItem(CombExamConfigDto combExamConfigDto) {
        List<CombExamConfigDetail> itemList = CommonUtils.convertList(combExamConfigDto.getCombExamConfigItemDtoList(), CombExamConfigDetail.class);
        for (CombExamConfigDetail item : itemList) {
            // 需要判断该题数目是否满足
            subjectService.isEnough(item.getCategoryId(), item.getSubjectTypeId(), item.getNum());
            item.setId(snowFlake.nextId());
            item.setCombExamConfigId(combExamConfigDto.getId());
        }
        CombExamConfig config = CommonUtils.copyProperties(combExamConfigDto, CombExamConfig.class);
        try {
            return save(config) && itemService.saveBatch(itemList);
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.COMB_EXAM_CONFIG_SAVE_FAIL);
        }
    }

    /**
     * 删除组卷配置
     *
     * @param dtoList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteCombExamConfig(List<CombExamConfigDto> dtoList) {
        List<CombExamConfig> configList = CommonUtils.convertList(dtoList, CombExamConfig.class);
        try {
            for (CombExamConfig config : configList) {
                itemService.removeByConfig(config.getId());
                baseMapper.removeWithOrg(config);
            }
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.COMB_EXAM_CONFIG_DELETE_FAIL);
        }
        return true;
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @FullCommonField(operation = EnumOperation.UPDATE)
    @Override
    public boolean updateCombExamConfig(CombExamConfigDto dto) {
        CombExamConfig config = CommonUtils.copyProperties(dto, CombExamConfig.class);
        List<CombExamConfigDetail> saveCombExamConfigDetailList = new ArrayList<>();
        List<CombExamConfigDetail> updateCombExamConfigDetailList = new ArrayList<>();
        for (CombExamConfigItemDto itemDto : dto.getCombExamConfigItemDtoList()) {
            subjectService.isEnough(itemDto.getCategoryId(), itemDto.getSubjectTypeId(), itemDto.getNum());
            CombExamConfigDetail item = CommonUtils.copyProperties(itemDto, CombExamConfigDetail.class);
            if (itemDto.getSave()) {
                item.setId(snowFlake.nextId());
                item.setCombExamConfigId(config.getId());
                saveCombExamConfigDetailList.add(item);
            } else {
                updateCombExamConfigDetailList.add(item);
            }
        }

        // 拿出删除的配置
        List<Long> deleted = dto.getDeleteIds();
        try {
            // 删除配置
            if (CommonUtils.notNull(deleted)) {
                itemService.removeByIds(deleted);
            }
            // 更新配置
            if (CommonUtils.notNull(updateCombExamConfigDetailList)) {
                itemService.updateBatchById(updateCombExamConfigDetailList);
            }
            // 插入的配置
            if (CommonUtils.notNull(saveCombExamConfigDetailList)) {
                itemService.saveBatch(saveCombExamConfigDetailList);
            }
        } catch (Exception e) {
            throw new BaseInfoException(BaseInfoError.COMB_EXAM_CONFIG_UPDATE_FAIL);
        }
        return true;
    }

    /**
     * @param combExamConfig
     * @return
     */
    @Override
    public List<CombExamConfig> listById(CombExamConfig combExamConfig) {
        combExamConfig.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        return baseMapper.queryCombExamConfig(combExamConfig);
    }
}
