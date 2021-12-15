package exam.demo.moduleuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.pojo.model.Position;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 职位表 - 数据访问接口
 *
 * @author gpmscloud
 */
public interface PositionMapper extends BaseMapper<Position> {
    /**
     * 查询职位集合
     *
     * @return 职位集合
     */
    List<UserOptionsDto> queryPosition(@Param("id") long id);


    List<Position> listByCompanyId(@Param("ids") List<Integer> ids);


    int update(Position position);


    @SelectProvider(type = PositionProvider.class, method = "batchSelect")
    int getLeafCount(List<Position> position);


    List<Position> queryOptions();

    int deleteById(Position position);

    @DeleteProvider(type = PositionProvider.class, method = "batchDelete")
    int deleteByIdList(List<Position> positions);

    class PositionProvider {
        /* 查询职位是否被使用 */
        public String batchSelect(Map map) {
            List<Position> positions = (List<Position>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(id) FROM user WHERE ");
            for (int i = 0; i < positions.size(); i++) {
                sb.append("position_id = ").append(positions.get(i).getId());
                if (i != positions.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }

        /* 批量删除 */
        public String batchDelete(Map map) {
            List<Position> positionList = (List<Position>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM position WHERE ");
            for (int i = 0; i < positionList.size(); i++) {
                sb.append("(id = ").append(positionList.get(i).getId()).append(" AND ")
                        .append("version = ").append(positionList.get(i).getVersion());
                if (positionList.get(i).getJudgeId() != null) {
                    sb.append(" AND ").append("company_id = ").append(positionList.get(i).getJudgeId()).append(")");
                } else {
                    sb.append(")");
                }
                if (i != positionList.size() - 1) {
                    sb.append(" OR ");
                }
            }
            return sb.toString();
        }
    }

    /**
     * 模糊查询及查询全部记录
     *
     * @param role 角色查询条件
     * @return 查询符合条件的角色记录集合
     */
    List<Position> query(Position role);
}
