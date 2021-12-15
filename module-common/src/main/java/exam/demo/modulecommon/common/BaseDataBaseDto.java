package exam.demo.modulecommon.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseDataBaseDto extends BaseDto {

    /**
     * 组织机构或者公司id
     */
    public Integer judgeId;
}
