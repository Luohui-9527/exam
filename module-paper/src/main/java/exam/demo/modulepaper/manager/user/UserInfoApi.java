package exam.demo.modulepaper.manager.user;

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
 * @since 2020-03-05
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_USER, path = ApiConstant.SERVICE_VALUE_USER)
public interface UserInfoApi {
    @PostMapping({"/get/user/name"})
    CommonResponse<CompanyAndUserVo> getUserInfo(List<Integer> request);

    @PostMapping({"/get/company/name/by/id"})
    CommonResponse<String> getUserNameById(Integer request);

    @PostMapping({"/get/scoring/officer"})
    CommonResponse<List<UserDto>> queryScoringOfficer(UserDto request);

    @PostMapping({"/get/id/by/name"})
    CommonResponse<Integer> getUserIdByName(String request);

    @PostMapping({"/get/company/name"})
    CommonResponse<String> getCompanyById(Integer request);
}
