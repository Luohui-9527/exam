package exam.demo.moduleuser.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import exam.demo.modulecommon.common.BaseQueryVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luohui
 * @version V1.0.0
 * @date 2019/8/28
 */
@Data
public class CompanyQueryVo extends BaseQueryVo implements Serializable {
    private static final long serialVersionUID = 2356517833861707124L;
    /**
     * 所属机构ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private String orgId;
    /**
     * 公司名
     */
    private String name;
    /**
     * 所属机构
     */
    private String orgName;

    public CompanyQueryVo() {
    }
}
