package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class UserOnlineInfoListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1655629590821725811L;
    /**
     * 在线ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
    private Integer stopTime;

    /**
     * 状态位
     */
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public UserOnlineInfoListVo() {
    }

    @Override
    public String toString() {
        return "UserOnlineInfoListVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", onlineTime=" + onlineTime +
                ", offlineTime=" + offlineTime +
                ", stopTime=" + stopTime +
                ", status=" + status +
                '}';
    }
}
