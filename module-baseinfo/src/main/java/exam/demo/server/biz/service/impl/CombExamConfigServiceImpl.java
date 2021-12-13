package exam.demo.server.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonField;
import exam.demo.modulecommon.common.CombExamConfigItemDto;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SnowFlake;
import exam.demo.server.biz.dao.CombExamConfigDao;
import exam.demo.server.biz.service.CombExamConfigItemService;
import exam.demo.server.biz.service.CombExamConfigService;
import exam.demo.server.biz.service.SubjectService;
import exam.demo.server.dto.CombExamConfigDto;
import exam.demo.server.exception.BaseInfoError;
import exam.demo.server.exception.BaseInfoException;
import exam.demo.server.pojo.model.CombExamConfig;
import exam.demo.server.pojo.model.CombExamConfigItem;
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
        List<CombExamConfigItem> itemList = CommonUtils.convertList(combExamConfigDto.getCombExamConfigItemDtoList(), CombExamConfigItem.class);
        for (CombExamConfigItem item : itemList) {
            // 需要判断该题数目是否满足
            subjectService.isEnough(item.getCategoryId(), item.getSubjectTypeId(), item.getNum());
            item.setId(snowFlake.nextId());
            item.setCombExamId(combExamConfigDto.getId());
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
        List<CombExamConfigItem> saveCombExamConfigItemList = new ArrayList<>();
        List<CombExamConfigItem> updateCombExamConfigItemList = new ArrayList<>();
        for (CombExamConfigItemDto itemDto : dto.getCombExamConfigItemDtoList()) {
            subjectService.isEnough(itemDto.getCategoryId(), itemDto.getSubjectTypeId(), itemDto.getNum());
            CombExamConfigItem item = CommonUtils.copyProperties(itemDto, CombExamConfigItem.class);
            if (itemDto.getSave()) {
                item.setId(snowFlake.nextId());
                item.setCombExamId(config.getId());
                saveCombExamConfigItemList.add(item);
            } else {
                updateCombExamConfigItemList.add(item);
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
            if (CommonUtils.notNull(updateCombExamConfigItemList)) {
                itemService.updateBatchById(updateCombExamConfigItemList);
            }
            // 插入的配置
            if (CommonUtils.notNull(saveCombExamConfigItemList)) {
                itemService.saveBatch(saveCombExamConfigItemList);
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
