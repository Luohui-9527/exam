package exam.demo.modulecommon.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import exam.demo.modulecommon.common.PageVo;

import java.util.List;

/**
 * @author luohui
 */
public class PageMapUtil {
    public static <T, K> PageVo<T> getPageMap(List<T> list, Page<K> page) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setPageInfo(list);
        pageVo.setSize(page.getSize());
        pageVo.setCurrent(page.getCurrent());
        long total = list.size();
        pageVo.setTotal(total);
        pageVo.setPages(total / page.getSize() + 1);
        return pageVo;
    }

}
