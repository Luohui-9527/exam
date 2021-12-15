package exam.demo.moduleexam.manage;


import exam.demo.modulecommon.common.CommonRequest;
import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CompanyAndUserVo;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-24
 */
@FeignClient(ApiConstant.SERVICE_NAME)
public interface UserApi {
    @PostMapping({"/get/user/name"})
    CommonResponse<CompanyAndUserVo> getUserInfo(CommonRequest<List<Integer>> request);

    @PostMapping({"/get/company/name/by/id"})
    CommonResponse<String> getUserNameById(CommonRequest<Integer> request);

    @PostMapping({"/get/scoring/officer"})
    CommonResponse<List<UserDto>> queryScoringOfficer(CommonRequest<UserDto> request);

    @PostMapping({"/get/id/by/name"})
    CommonResponse<Integer> getUserIdByName(CommonRequest<String> request);

    @PostMapping({"/get/company/name"})
    CommonResponse<String> getCompanyById(CommonRequest<Integer> request);
}
