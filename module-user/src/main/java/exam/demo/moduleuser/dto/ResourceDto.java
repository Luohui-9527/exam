package exam.demo.moduleuser.dto;


import exam.demo.modulecommon.common.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * t_resource
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -1823275686062875376L;
    /**
     * 资源节点名称
     */
    private String name;

    /**
     * 节点编号
     */
    private String code;

    /**
     * 父亲节点
     */
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
     * 顺序号
     */
    private Long orderIndex;

    /**
     * 资源类型
     */
    private Byte resourceType;

    /**
     * 叶子节点
     */
    private Byte leaf;

    /**
     * 备注
     */
    private String remark;

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

}
