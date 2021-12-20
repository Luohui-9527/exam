package exam.demo.moduleauth.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户权限关联表 - 数据对象定义
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Data
@TableName("user_role")
public class UserRole implements Serializable {

    /**
     * 用户权限关联id
     */
    @ApiModelProperty(value = "用户权限关联id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Long roleId;
}
