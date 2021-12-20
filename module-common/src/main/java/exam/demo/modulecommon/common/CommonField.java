package exam.demo.modulecommon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.persistence.Id;
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
     * 雪花算法生成Id
     */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    public Long id;
    /**
     * 机构id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long orgId;
    /**
     * 机构下公司id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long companyId;
    /**
     * 通过id到数据字典中查询创建者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long createdBy;
    /**
     * 创建日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date createdTime;
    /**
     * 通过id到数据字典中查询修改者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    public Long updatedBy;
    /**
     * 修改日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    public Date updatedTime;
    /**
     * 版本，为Date.getTime()
     */
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long version;
}
