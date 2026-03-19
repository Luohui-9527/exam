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
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long paperType;
    private String paperTypeName;
    private Double score;
    private Integer status;
    private Date createdTime;
}
