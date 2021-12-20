package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.Resource;
import org.apache.ibatis.annotations.DeleteProvider;

import java.util.List;
import java.util.Map;

/**
 * 资源表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 模糊查询及查询全部记录
     *
     * @param resource 资源查询条件
     * @return 查询符合条件的资源记录集合
     */
    List<Resource> query(Resource resource);

    @DeleteProvider(type = ResourceProvider.class, method = "batchDelete")
    int deleteByIdList(List<Resource> resources);

    class ResourceProvider {
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Resource> resources = (List<Resource>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM resource WHERE ");
            for (int i = 0; i < resources.size(); i++) {
                sb.append("(id = ").append(resources.get(i).getId()).append(" AND ")
                        .append("version = ").append(resources.get(i).getVersion()).append(")");
                if (i != resources.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
