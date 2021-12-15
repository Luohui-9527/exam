package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限资源关联表 - 数据对象定义
 *
 * @author gpmscloud
 */
@Data
@TableName("role_resource")
public class RoleResource implements Serializable {

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Integer id;
    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Integer roleId;
    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    private Integer resourceId;
    /**
     * 是否半选
     */
    @ApiModelProperty(value = "是否半选")
    private Integer type;
}
