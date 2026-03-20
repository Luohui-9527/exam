package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class UserOnlineInfoListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1655629590821725811L;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
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
