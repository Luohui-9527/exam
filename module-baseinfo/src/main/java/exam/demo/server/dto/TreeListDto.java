package exam.demo.server.dto;

import lombok.Data;

/**
 * 树列表DTO
 */
@Data
public class TreeListDto {

    /**
     * id
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父亲id
     */
    private Integer parentId;

    /**
     * 版本
     */
    private Long version;

}
