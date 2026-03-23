package exam.demo.modulecommon.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import exam.demo.modulecommon.common.PageVo;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageUtil {

    public static <T, R> PageVo<R> convertPage(IPage<T> page, Function<T, R> converter) {
        PageVo<R> pageVo = new PageVo<>();
        pageVo.setTotal(page.getTotal());
        pageVo.setCurrent(page.getCurrent());
        pageVo.setSize(page.getSize());
        pageVo.setPages(page.getPages());

        List<R> records = page.getRecords().stream()
                .map(converter)
                .collect(Collectors.toList());
        pageVo.setPageInfo(records);

        return pageVo;
    }

    public static <T> PageVo<T> fromIPage(IPage<T> page) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setTotal(page.getTotal());
        pageVo.setCurrent(page.getCurrent());
        pageVo.setSize(page.getSize());
        pageVo.setPages(page.getPages());
        pageVo.setPageInfo(page.getRecords());
        return pageVo;
    }
}
