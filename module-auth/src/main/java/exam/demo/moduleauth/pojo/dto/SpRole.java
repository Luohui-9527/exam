package exam.demo.moduleauth.pojo.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luohui
 * @version 1.0
 * @since 2020-04-17
 */
public class SpRole {
    /**
     * 角色ID
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;

    Set<SpResource> resources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SpResource> getResources() {
        return resources;
    }

    public void setResources(Set<SpResource> resources) {
        this.resources = resources;
    }
}
