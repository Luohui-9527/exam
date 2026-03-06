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
        
        // 计算总记录数
        long total = 0;
        if (list != null) {
            total = list.size();
        }
        pageVo.setTotal(total);
        
        // 计算总页数
        long pages = total / page.getSize();
        if (total % page.getSize() > 0) {
            pages++;
        }
        pageVo.setPages(pages);
        
        // 设置当前页和每页大小
        pageVo.setCurrent(page.getCurrent());
        pageVo.setSize(page.getSize());
        
        // 进行分页处理
        List<T> pageList = new java.util.ArrayList<>();
        if (list != null && !list.isEmpty()) {
            // 计算起始索引和结束索引
            int startIndex = (int) ((page.getCurrent() - 1) * page.getSize());
            int endIndex = (int) Math.min(startIndex + page.getSize(), total);
            
            // 截取数据
            if (startIndex < endIndex) {
                pageList = list.subList(startIndex, endIndex);
            }
        }
        
        pageVo.setPageInfo(pageList);
        return pageVo;
    }

}
