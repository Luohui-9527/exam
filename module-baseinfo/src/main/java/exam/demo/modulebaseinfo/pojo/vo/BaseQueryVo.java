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
     * 每页大小（小写，用于接收请求参数）
     */
    @JsonProperty("pagesize")
    public void setPagesize(Long pagesize) {
        this.pageSize = pagesize;
    }

}
