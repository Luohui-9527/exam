package exam.demo.moduleuser.dto;


import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user_online_info
 *
 * @author
 */
@Data
public class UserOnlineInfoDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = 4288047899945380612L;
    /**
     * 在线ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 工号
     */
    private String code;

    /**
     * 用户名
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 上线时间
     */
    private Date onlineTime;

    /**
     * 下线时间
     */
    private Date offlineTime;

    /**
     * 在线时长
     */
    private Integer stopTime;

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

    private Integer judgeId;

}
