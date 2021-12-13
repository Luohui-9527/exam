package exam.demo.modulecommon.common;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息返回
 *
 * @author luohui
 */
@Data
public class PageVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 每页记录条数
     */
    private Long size;
    /**
     * 当前页码，从1开始
     */
    private Long current;
    /**
     * 总页数
     */
    private Long pages;
    /**
     * 记录列表
     */
    private List<T> pageInfo;

    public PageVo() {
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        this.pages = 0L;
        this.pageInfo = new ArrayList<>();
    }

    public Long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }
            return pages;
        }
    }
}
