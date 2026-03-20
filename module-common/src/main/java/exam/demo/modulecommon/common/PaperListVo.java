package exam.demo.modulecommon.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaperListVo {
    private String id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private String paperType;
    private String paperTypeName;
    private Double score;
    private Integer status;
    private Date createdTime;
}
