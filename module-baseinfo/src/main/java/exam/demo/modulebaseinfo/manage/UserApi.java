package exam.demo.modulebaseinfo.manage;


import exam.demo.modulebaseinfo.constant.ApiConstant;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CompanyAndUserVo;
import exam.demo.modulecommon.common.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-05
 */
@FeignClient(value = ApiConstant.SERVICE_NAME)
public interface UserApi {
    @PostMapping({"/user/get/user/name"})
    CommonResponse<CompanyAndUserVo> getUserInfo(List<Long> request);

    @PostMapping({"/user/get/company/name/by/id"})
    CommonResponse<String> getUserNameById(Long request);

    @PostMapping({"/user/get/scoring/officer"})
    CommonResponse<List<UserDto>> queryScoringOfficer(UserDto request);

    @PostMapping({"/user/get/id/by/name"})
    CommonResponse<Long> getUserIdByName(String request);

    @PostMapping({"/user/get/company/name"})
    CommonResponse<String> getCompanyById(Long request);
}
