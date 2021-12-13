package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.exception.StarterError;
import exam.demo.modulecommon.exception.StarterException;
import exam.demo.modulecommon.utils.AdminUtil;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.SqlUtil;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.modulecommon.utils.jwt.UserPermission;
import exam.demo.moduleuser.biz.dao.PositionDao;
import exam.demo.moduleuser.biz.service.CompanyService;
import exam.demo.moduleuser.biz.service.PositionService;
import exam.demo.moduleuser.dto.CompanyDto;
import exam.demo.moduleuser.dto.PositionDto;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.Company;
import exam.demo.moduleuser.pojo.model.Position;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position> implements PositionService {
    @Autowired
    CompanyService companyService;


    @FullCommonFieldU
    @Override
    public boolean save(PositionDto positionDto) {
        Position position = CommonUtils.copyProperties(positionDto, Position.class);
        if (save(position)) {
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(PositionDto positionDto) {
        Position position = CommonUtils.copyProperties(positionDto, Position.class);
        position.setJudgeId(CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId()) ? null : CommonUtils.judgeCompanyAndOrg());
        if (baseMapper.update(position) == 1) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<Position> positionList) {
        for (Position position : positionList) {
            position.setJudgeId(CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId()) ? null : CommonUtils.judgeCompanyAndOrg());
        }
        if (baseMapper.getLeafCount(positionList) > 0) {
            throw new UserException(UserError.RECORD_IS_IN_USE);
        }
        if (baseMapper.deleteByIdList(positionList) == positionList.size()) {
            return true;
        }
        throw new UserException(UserError.DELETE_FAIL);
    }

    @Override
    public List<Position> list(Position position) {
        if (AdminUtil.isSuperAdmin()) {
            List<Company> companyList = companyService.list();
            List<Position> positionList = list();
            for (Position position1 : positionList) {
                for (Company company : companyList) {
                    if (position1.getCompanyId().equals(company.getId())) {
                        position1.setCompanyName(company.getName());
                        break;
                    }
                }
            }
            return positionList;
        }
        // 根据Id或者名称模糊查询，当条件都没有时，根据操作者的机构/公司id查询,最后过滤名称
        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        if (position.getId() != null) {
            positionQueryWrapper.eq("id", position.getId());
            return list(positionQueryWrapper);
        }
        UserPermission userPermission = TokenUtils.getUser();
        if (userPermission.getCompanyId() != null) {
            positionQueryWrapper.eq("company_id", userPermission.getCompanyId());
            if (StringUtils.isNotBlank(position.getName())) {
                positionQueryWrapper.likeRight("name", position.getName());
            }
            List<Position> positionList = list(positionQueryWrapper);
            Company company = companyService.getById(userPermission.getCompanyId());
            for (Position position1 : positionList) {
                position1.setCompanyName(company.getName());
            }
            return positionList;
        } else if (userPermission.getOrgId() != null) {
            // 查该机构下的所有公司
            List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(userPermission.getOrgId()).build());
            List<Long> companyIdList = companyList.stream().map(Company::getId).collect(Collectors.toList());
            if (CommonUtils.isEmpty(companyIdList)) {
                return null;
            }
            List<Position> positionList = baseMapper.listByCompanyId(companyIdList);
            positionList = positionList.stream().filter(p -> SqlUtil.like(position.getName(), p.getName())).collect(Collectors.toList());
            for (Position position1 : positionList) {
                for (Company company : companyList) {
                    if (position1.getCompanyId().equals(company.getId())) {
                        position1.setCompanyName(company.getName());
                        break;
                    }
                }
            }
            return positionList;
        }
        throw new StarterException(StarterError.SYSTEM_ACCESS_INVALID);
    }

    @Override
    public List<Position> listCompany() {
        return baseMapper.queryOptions();
    }

    /**
     * 查询职位
     *
     * @return 职位集合
     */
    @Override
    public List<UserOptionsDto> queryPosition() {
        List<Company> companyList = companyService.listByOrgId(TokenUtils.getUser().getOrgId());
        List<UserOptionsDto> res = new ArrayList<>();
        for (Company company : companyList) {
            res.addAll(baseMapper.queryPosition(company.getId()));
        }
        return res;
    }
}
