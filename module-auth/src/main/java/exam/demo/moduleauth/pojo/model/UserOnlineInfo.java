package exam.demo.moduleauth.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 用户在线记录表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("user_online_info")
public class UserOnlineInfo implements Serializable {

    /**
     * 在线ID
     */
    @ApiModelProperty(value = "在线ID")
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    private String code;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;
    /**
     * IP
     */
    @ApiModelProperty(value = "IP")
    private String ip;
    /**
     * 上线时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "上线时间")
    private java.util.Date onlineTime;
    /**
     * 下线时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "下线时间")
    private java.util.Date offlineTime;
    /**
     * 在线时长
     */
    @ApiModelProperty(value = "在线时长")
    private Long stopTime;
    /**
     * 状态位
     */
    @ApiModelProperty(value = "状态位")
    private Integer status;
}
