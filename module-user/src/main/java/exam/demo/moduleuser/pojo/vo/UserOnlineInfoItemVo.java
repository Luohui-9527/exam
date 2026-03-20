package exam.demo.moduleuser.pojo.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class UserOnlineInfoItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -5863202679316345503L;
    /**
     * 在线ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

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
    private Long stopTime;

    /**
     * 状态位
     */
    private Byte status;

}
