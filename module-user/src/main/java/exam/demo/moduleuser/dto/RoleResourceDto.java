package exam.demo.moduleuser.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author acer
 */
@Data
public class RoleResourceDto implements Serializable {
    private static final Long serialVersionUID = -1857819275686602576L;
    private String id;
    private String roleId;
    private String resourceId;
    private Byte type;
}
