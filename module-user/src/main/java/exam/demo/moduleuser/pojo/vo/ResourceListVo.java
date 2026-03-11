package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceListVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 4163387454235973715L;
    /**
     * 资源ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 顺序号
     */
    private Long orderIndex;

    /**
     * 父亲节点
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * URL
     */
    private String url;

    /**
     * 打开图标
     */
    private String openImg;

    /**
     * 关闭图标
     */
    private String closeImg;

    /**
     * 资源类型
     */
    private Long resourceType;

    /**
     * 叶子节点
     */
    private Long leaf;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态位
     */
    private Long status;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public ResourceListVo() {
    }
}
