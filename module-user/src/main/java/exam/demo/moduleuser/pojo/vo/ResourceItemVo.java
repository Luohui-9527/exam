package exam.demo.moduleuser.pojo.vo;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class ResourceItemVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = -8678302067843260897L;
    /**
     * 资源ID
     */
    private String id;

    /**
     * 节点名称
     */
    @NotNull(message = "节点名称不能为空")
    private String name;

    /**
     * 编号
     */
    @NotNull(message = "节点编号不能为空")
    private String code;

    /**
     * 顺序号
     */
    @NotNull(message = "顺序号不能为空")
    private Long orderIndex;

    /**
     * 父亲节点
     */
    private String parentId;

    /**
     * URL
     */
    @NotNull(message = "url不能为空")
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
    @NotNull(message = "资源类型不能为空")
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

    public ResourceItemVo() {
    }

}
