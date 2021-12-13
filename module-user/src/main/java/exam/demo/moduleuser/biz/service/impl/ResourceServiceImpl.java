package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.moduleuser.biz.dao.ResourceDao;
import exam.demo.moduleuser.biz.service.ResourceService;
import exam.demo.moduleuser.dto.ResourceDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, Resource> implements ResourceService {
    @FullCommonFieldU
    @Override
    public boolean save(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto, Resource.class);
        if (save(resource)) {
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<Resource> resources) {
        if (baseMapper.getLeafCount(resources) > 0) {
            throw new UserException(UserError.RESOURCE_IS_IN_USE);
        }
        if (baseMapper.deleteByIdList(resources) == resources.size()) {
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto, Resource.class);
        if (baseMapper.updateResource(resource) == 1) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Override
    public List<Resource> list(ResourceDto resourceDto) {
        Resource resource = CommonUtils.copyProperties(resourceDto, Resource.class);
        return baseMapper.query(resource);
    }

    @Override
    public List<TreeListDto> getQueryList() {
        List<Resource> resourceList = list();
        return CommonUtils.convertList(resourceList, TreeListDto.class);
    }

    @Override
    public List<Resource> listByIdList(List<Long> idList) {
        return baseMapper.listByIdList(idList);
    }
}

