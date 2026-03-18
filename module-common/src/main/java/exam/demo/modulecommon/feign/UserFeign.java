package exam.demo.modulecommon.feign;


import exam.demo.modulecommon.common.CommonResponse;
import exam.demo.modulecommon.common.CompanyAndUserVo;
import exam.demo.modulecommon.common.UserDto;
import exam.demo.modulecommon.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务 Feign 客户端
 * 用于内部调用 user 服务
 *
 * @author luohui
 */
@FeignClient(name = ApiConstant.SERVICE_NAME_USER)
public interface UserFeign {
    @PostMapping({"/get/user/name"})
    CommonResponse<CompanyAndUserVo> getUserInfo(@RequestBody List<Long> request);

    @PostMapping({"/get/company/name/by/id"})
    CommonResponse<String> getUserNameById(@RequestBody Long request);

    @PostMapping({"/get/scoring/officer"})
    CommonResponse<List<UserDto>> queryScoringOfficer(@RequestBody UserDto request);

    @PostMapping({"/get/id/by/name"})
    CommonResponse<Long> getUserIdByName(@RequestBody String request);

    @PostMapping({"/get/company/name"})
    CommonResponse<String> getCompanyById(@RequestBody Long request);
}
