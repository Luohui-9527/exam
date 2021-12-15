package exam.demo.moduleuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.*;
import exam.demo.moduleuser.dto.OrganizationDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.mapper.OrganizationMapper;
import exam.demo.moduleuser.pojo.model.Organization;
import exam.demo.moduleuser.service.IOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织机构表 - 服务实现
 *
 * @author gpmscloud
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements IOrganizationService {
    @FullCommonFieldU
    @Override
    public boolean save(OrganizationDto organizationDto) {
        Organization organization = CommonUtils.copyProperties(organizationDto, Organization.class);
        if (save(organization)) {
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(OrganizationDto organizationDto) {
        Organization organization = CommonUtils.copyProperties(organizationDto, Organization.class);
        organization.setJudgeId(CommonUtils.judgeCompanyAndOrg());
        if (updateById(organization)) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<OrganizationDto> list) {
        List<Integer> ids = list.stream().map(OrganizationDto::getId).collect(Collectors.toList());
        try {
            if (removeByIds(ids)) {
                return true;
            }
        } catch (Exception e) {
            if (ExceptionUtils.isForeignKeyViolation(e)) {
                throw new UserException(UserError.ORG_IS_IN_USE);
            }
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Override
    public List<Organization> list(OrganizationDto organizationDto) {
        if (AdminUtil.isSuperAdmin()) {
            List<Organization> organizationList = list();
            if (StringUtils.isNotBlank(organizationDto.getName())) {
                organizationList = organizationList.stream().filter(o -> SqlUtil.like(organizationDto.getName(), o.getName())).collect(Collectors.toList());
            }
            return organizationList;
        }
        Organization organization = CommonUtils.copyProperties(organizationDto, Organization.class);
        List<Organization> organizationList = baseMapper.query(organization);
        return organizationList.stream().filter(o ->
                o.getId().equals(TokenUtils.getUser().getOrgId())
        ).collect(Collectors.toList());
    }

    @Override
    public String getNameById(long id) {
        return baseMapper.getById(id);
    }
}
