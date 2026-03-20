package exam.demo.moduleuser.pojo.vo;


import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAlloctionQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -4104146631752309115L;
    /**
     * 角色ID
     */
    private String id;
    /**
     * 用户工号
     */
    private String code;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 公司Id
     */
    private String companyId;
    /**
     * 组织机构ID
     */
    private String orgId;

}
