package exam.demo.modulebaseinfo.dto;

import lombok.Data;

/**
 * 树列表DTO
 */
@Data
public class TreeListDto {

    /**
     * id
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 父亲id
     */
    private Long parentId;

    /**
     * 版本
     */
    private Long version;

}
