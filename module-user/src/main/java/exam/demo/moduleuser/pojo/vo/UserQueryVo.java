package exam.demo.moduleuser.pojo.vo;

import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class UserQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = 1710678390025284785L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 工号
     */
    private String code;
    /**
     * 手机号
     */
    private String tel;
    /**
     * 角色代号
     */
    private String roleCode;

    /**
     * 角色ID
     */
    private String roleId;

    public UserQueryVo() {
    }

}
