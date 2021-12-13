package exam.demo.server.manage;


import exam.demo.server.constant.ApiConstant;
import exam.demo.modulecommon.common.CommonRequest;
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
    CommonResponse<CompanyAndUserVo> getUserInfo(CommonRequest<List<Long>> request);

    @PostMapping({"/user/get/company/name/by/id"})
    CommonResponse<String> getUserNameById(CommonRequest<Long> request);

    @PostMapping({"/user/get/scoring/officer"})
    CommonResponse<List<UserDto>> queryScoringOfficer(CommonRequest<UserDto> request);

    @PostMapping({"/user/get/id/by/name"})
    CommonResponse<Long> getUserIdByName(CommonRequest<String> request);

    @PostMapping({"/user/get/company/name"})
    CommonResponse<String> getCompanyById(CommonRequest<Long> request);
}
