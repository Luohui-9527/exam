package exam.demo.moduleuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import exam.demo.moduleuser.dto.PositionDto;
import exam.demo.moduleuser.dto.UserOptionsDto;
import exam.demo.moduleuser.pojo.model.Position;

import java.util.List;

/**
 * 职位表 - 服务接口
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

public interface IPositionService extends IService<Position> {
    /**
     * 功能描述
     *
     * @param positionDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean save(PositionDto positionDto);

    /**
     * 功能描述
     *
     * @param positionDto
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean update(PositionDto positionDto);

    /**
     * 功能描述
     *
     * @param positionList
     * @return: boolean
     * @Author: luohui
     * @date: 2021-12-9
     */
    boolean delete(List<Position> positionList);

    /**
     * 功能描述
     *
     * @param position
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Position>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Position> list(Position position);

    /**
     * 功能描述
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.pojo.model.Position>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<Position> listCompany();

    /**
     * 功能描述 查询职位
     *
     * @param
     * @return: java.util.List<exam.demo.moduleuser.dto.UserOptionsDto>
     * @Author: luohui
     * @date: 2021-12-9
     */
    List<UserOptionsDto> queryPosition();
}
