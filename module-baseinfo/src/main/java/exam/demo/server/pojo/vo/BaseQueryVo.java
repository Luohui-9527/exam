package exam.demo.server.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询VO基类
 *
 * @author luohui
 */
@Data
public class BaseQueryVo implements Serializable {


    private static final long serialVersionUID = -8750679123979588164L;
    /**
     * 当前分页
     */
    @NotNull(message = "当前分页")
    protected Integer pageNum;

    /**
     * 每页大小
     */
    @NotNull(message = "每页大小")
    protected Integer pageSize;

}
