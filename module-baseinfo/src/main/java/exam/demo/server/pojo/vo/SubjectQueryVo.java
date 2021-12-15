package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目查询VO
 *
 * @author luohui
 */
@Builder
@AllArgsConstructor
@Data
public class SubjectQueryVo extends BaseQueryVo implements Serializable {

    private static final long serialVersionUID = 1902508370783136321L;

    /**
     * 题目id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;
    /**
     * 题目
     */
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer difficulty;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer subjectTypeId;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer categoryId;


    /**
     * 非表对应字段
     * 题目类型名字
     */
    private String subjectTypeName;

    /**
     * 非表对应字段
     * 题目类别名字
     */
    private String categoryName;

    /**
     * 非表对应字段
     * 题目难度名字
     */
    private String difficultyName;

    /**
     * 修改日期
     */
    public Date updatedTime;

    /**
     * 题目类型属性列
     */
    private String attribute;

    /**
     * 状态位
     */
    private Byte status;

    private String remark;

    public SubjectQueryVo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(Integer subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

}
