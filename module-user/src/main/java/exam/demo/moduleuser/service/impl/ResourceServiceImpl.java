package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.constant.MagicPointConstant;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.moduleuser.dto.ResourceDto;
import exam.demo.moduleuser.dto.TreeListDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.mapper.ResourceMapper;
import exam.demo.moduleuser.pojo.model.Resource;
import exam.demo.moduleuser.service.IResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

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
        if (this.getLeafCount(resources) > 0) {
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
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.eq(MagicPointConstant.VERSION, resource.getVersion());
        if (baseMapper.update(resource, wrapper) == 1) {
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
        return this.listByIds(idList);
    }

    private long getLeafCount(List<Resource> resources) {
        List<Long> parentIdList = resources.stream().map(Resource::getParentId).collect(Collectors.toList());
        QueryWrapper<Resource> wrapper = new QueryWrapper<>();
        wrapper.in(MagicPointConstant.PARENT_ID, parentIdList);
        return this.count(wrapper);
    }
}

