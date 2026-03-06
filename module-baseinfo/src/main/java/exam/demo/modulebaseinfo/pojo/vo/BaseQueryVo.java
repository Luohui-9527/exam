package exam.demo.modulebaseinfo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
    protected Long pageNum;

    /**
     * 每页大小
     */
    @JsonProperty("pageSize")
    protected Long pageSize;

    /**
     * 获取当前分页，若为null则返回默认值1
     *
     * @return 当前分页
     */
    public Long getPageNumOrDefault() {
        return pageNum != null ? pageNum : 1L;
    }

    /**
     * 获取每页大小，若为null则返回默认值10
     *
     * @return 每页大小
     */
    public Long getPageSizeOrDefault() {
        return pageSize != null ? pageSize : 10L;
    }

}
