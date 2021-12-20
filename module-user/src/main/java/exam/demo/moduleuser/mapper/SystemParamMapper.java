package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.pojo.model.SystemParam;
import exam.demo.moduleuser.pojo.model.TreeList;
import org.apache.ibatis.annotations.DeleteProvider;

import java.util.List;
import java.util.Map;

/**
 * 系统配置表 - 数据访问接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface SystemParamMapper extends BaseMapper<SystemParam> {


    @DeleteProvider(type = SystemParamProvider.class, method = "batchDelete")
    int deleteByIdList(List<SystemParam> systemParamList);

    List<TreeList> getQueryListData();

    class SystemParamProvider {
        /* 批量删除 */
        public String batchDelete(Map map) {
            List<SystemParam> systemParams = (List<SystemParam>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM system_param WHERE ");
            for (int i = 0; i < systemParams.size(); i++) {
                sb.append("(id = ").append(systemParams.get(i).getId()).append(" AND ")
                        .append("version = ").append(systemParams.get(i).getVersion()).append(")");
                if (i != systemParams.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }
}
