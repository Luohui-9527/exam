package exam.demo.moduleuser.biz.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.modulecommon.annotation.FullCommonFieldU;
import exam.demo.modulecommon.enums.EnumOperation;
import exam.demo.modulecommon.utils.AdminUtil;
import exam.demo.modulecommon.utils.CommonUtils;
import exam.demo.modulecommon.utils.ExceptionUtils;
import exam.demo.modulecommon.utils.TokenUtils;
import exam.demo.moduleuser.biz.dao.DepartmentDao;
import exam.demo.moduleuser.biz.service.CompanyService;
import exam.demo.moduleuser.biz.service.DepartmentService;
import exam.demo.moduleuser.biz.service.OrganizationService;
import exam.demo.moduleuser.dto.CompanyDto;
import exam.demo.moduleuser.dto.DepartmentDto;
import exam.demo.moduleuser.exception.UserError;
import exam.demo.moduleuser.exception.UserException;
import exam.demo.moduleuser.pojo.model.Company;
import exam.demo.moduleuser.pojo.model.Department;
import exam.demo.moduleuser.pojo.model.Organization;
import exam.demo.moduleuser.pojo.model.TreeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-03-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {
    @Resource
    DepartmentDao departmentDao;

    @Autowired
    OrganizationService organizationService;


    @Autowired
    CompanyService companyService;

    @FullCommonFieldU
    @Override
    public boolean save(DepartmentDto departmentDto) {
        Department department = CommonUtils.copyProperties(departmentDto, Department.class);
        if (save(department)) {
            return true;
        }
        throw new UserException(UserError.SAVE_FAIL);
    }

    @FullCommonFieldU(operation = EnumOperation.UPDATE)
    @Override
    public boolean update(DepartmentDto departmentDto) {
        Department department = CommonUtils.copyProperties(departmentDto, Department.class);
        if (baseMapper.update(department) == 1) {
            return true;
        }
        throw new UserException(UserError.UPDATE_FAIL);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(List<DepartmentDto> departmentDtoList) {
        List<Department> departments = CommonUtils.convertList(departmentDtoList, Department.class);
        for (Department department : departments) {
            department.setJudgeId(CommonUtils.judgeCompanyAndOrg());
            if (department.getJudgeId().equals(TokenUtils.getUser().getOrgId())) {
                department.setJudgeId(null);
            }
        }
        try {
            if (baseMapper.deleteByIdList(departments) < departmentDtoList.size()) {
                throw new UserException(UserError.DELETE_FAIL);
            }
        } catch (Exception e) {
            if (ExceptionUtils.isForeignKeyViolation(e)) {
                throw new UserException(UserError.EXIST_WORKER);
            }
        }
        return true;
    }

    @Override
    public List<Department> query(Department department) {
        if (AdminUtil.isSuperAdmin()) {
            return list();
        }
        if (department.getJudgeId().equals(TokenUtils.getUser().getOrgId())) {
            department.setJudgeId(null);
        }
        List<Department> data = baseMapper.query(department);
        // 去掉不是该用户的机构的数据 id为companyId
        List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(TokenUtils.getUser().getOrgId()).build());
        List<Department> res = new ArrayList<>();
        for (Company company : companyList) {
            for (Department d : data) {
                if (d.getCompanyId().equals(company.getId())) {
                    res.add(d);
                }
            }
        }
        return res;
    }

    @Override
    public List<Department> queryLevel() {
        return baseMapper.queryLevel();
    }

    @Override
    public List<Department> queryParent() {
        return baseMapper.queryParent();
    }

    @Override
    public List<TreeList> queryTreeData() {
        // 超级管理员
        if (AdminUtil.isSuperAdmin()) {
            List<TreeList> res = new ArrayList<>();
            // 列出所有机构
            List<Organization> organizationList = organizationService.list();
            for (Organization organization : organizationList) {
                // 列出该机构所有公司
                List<Company> companyList = companyService.listByOrgId(organization.getId());
                for (Company company : companyList) {
                    // 列出该公司下所有部门
                    List<Department> departmentList = departmentDao.listByCompanyId(company.getId());
                    for (Department department : departmentList) {
                        // 将其变成TreeList
                        res.add(TreeList.builder().name(department.getName()).id(department.getId())
                                .parentId(company.getId()).rootId(company.getId()).build());
                    }
                    res.add(TreeList.builder().id(company.getId()).name(company.getName())
                            .parentId(organization.getId()).rootId(organization.getId()).build());
                }
                res.add(TreeList.builder().id(organization.getId()).name(organization.getName()).build());
            }
            return res;
        }
        Long id = null;
        if (!CommonUtils.judgeCompanyAndOrg().equals(TokenUtils.getUser().getOrgId())) {
            id = CommonUtils.judgeCompanyAndOrg();
        }
        List<TreeList> data = departmentDao.getQueryListData(id);
        // 去掉不是该用户的机构的数据 id为companyId
        List<Company> companyList = companyService.queryCompany(CompanyDto.builder().orgId(TokenUtils.getUser().getOrgId()).build());
        List<TreeList> res = new ArrayList<>();
        for (Company company : companyList) {
            for (TreeList treeList : data) {
                if (treeList.getId().equals(company.getId())) {
                    res.add(treeList);
                }
            }
        }
        return res;
    }
}
