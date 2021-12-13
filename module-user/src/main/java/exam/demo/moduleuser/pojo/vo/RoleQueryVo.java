package exam.demo.moduleuser.pojo.vo;


import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class RoleQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = -3259773252430891860L;
    /**
     * 角色名称
     */
    private String name;

    public RoleQueryVo() {
    }

    @Override
    public String toString() {
        return "RoleQueryVo{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
