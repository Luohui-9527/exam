package exam.demo.moduleuser.dto;


import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * t_system_param
 *
 * @author
 */
@Data
public class SystemParamDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 1271364634275015815L;
    /**
     * 系统参数ID
     */
    private Long id;

    /**
     * 组织机构ID
     */
    private Long orgId;

    /**
     * 参数类型
     */
    private Long paramType;

    private String paramTypeName;

    /**
     * 参数项
     */
    private String param;

    /**
     * 参数值
     */
    private String value;

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

    private Long judgeId;

    private Long oldVersion;
}
