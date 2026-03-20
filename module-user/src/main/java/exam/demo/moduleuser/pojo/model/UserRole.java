package exam.demo.moduleuser.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
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
    private String id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private String roleId;

    @TableField(exist = false)
    private String companyId;
}
