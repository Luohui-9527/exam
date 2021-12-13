package exam.demo.moduleuser.pojo.vo;


import exam.demo.modulecommon.common.BaseQueryVo;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
public class OrganizationQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = 5089335695189062663L;
    /**
     * 组织机构名
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationQueryVo() {
    }

    @Override
    public String toString() {
        return "OrganizationQueryVo{" +
                "name='" + name + '\'' +
                '}';
    }
}
