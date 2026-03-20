package exam.demo.modulecommon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * 表中共有字段
 *
 * @author luohui
 * @version 1.0
 * @since 2019-08-12
 */
@Data
public class CommonField {

    /**
     * UUID生成Id
     */
    public String id;
    /**
     * 机构id
     */
    public String orgId;
    /**
     * 机构下公司id
     */
    public String companyId;
    /**
     * 通过id到数据字典中查询创建者
     */
    public String createdBy;
    /**
     * 创建日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date createdTime;
    /**
     * 通过id到数据字典中查询修改者
     */
    public String updatedBy;
    /**
     * 修改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date updatedTime;
    /**
     * 版本，为Date.getTime()
     */
    protected Long version;
}
