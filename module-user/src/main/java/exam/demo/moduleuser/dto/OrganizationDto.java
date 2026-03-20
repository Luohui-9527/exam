package exam.demo.moduleuser.dto;

import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * t_organization
 *
 * @author
 */
@Data
public class OrganizationDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -2422855575959269722L;
    /**
     * 组织机构ID
     */
    private String id;

    /**
     * 机构名
     */
    private String name;

    /**
     * 机构代码
     */
    private String code;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String tel;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态位
     */
    private Byte status;

    /**
     * 当前请求页
     */
    private int currentPage;

    /**
     * 页面显示条数
     */
    private int pageSize;

    private String judgeId;

}
