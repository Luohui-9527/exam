package exam.demo.server.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 题目VO
 *
 * @Date: 2019/9/5
 * @Version: 1.0
 * @Maintenance Records:
 */
public class SubjectVo extends BaseItemVo implements Serializable {

    private static final long serialVersionUID = 8413924345677180367L;

    /**
     * 题目
     */
    @NotBlank(message = "题目内容不能为空！")
    private String name;

    /**
     * 题目类型id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题型不能为空！")
    private Integer subjectTypeId;

    /**
     * 题目类型名
     */
    private String subjectTypeName;

    /**
     * 难度id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题目难度不能为空！")
    private Integer difficulty;

    /**
     * 题目类别id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "题目类别不能为空！")
    private Integer categoryId;

    /**
     * 题目类别名
     */
    private String categoryName;

    @Valid
    private List<SubjectAnswerVo> subjectAnswerVoList;

    public SubjectVo() {
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

    public String getSubjectTypeName() {
        return subjectTypeName;
    }

    public void setSubjectTypeName(String subjectTypeName) {
        this.subjectTypeName = subjectTypeName;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SubjectAnswerVo> getSubjectAnswerVOList() {
        return subjectAnswerVoList;
    }

    public void setSubjectAnswerVOList(List<SubjectAnswerVo> subjectAnswerVOList) {
        this.subjectAnswerVoList = subjectAnswerVOList;
    }

    @Override
    public String toString() {
        return "SubjectVO{" +
                "name='" + name + '\'' +
                ", subjectTypeId=" + subjectTypeId +
                ", subjectTypeName='" + subjectTypeName + '\'' +
                ", difficulty=" + difficulty +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", subjectAnswerVOList=" + subjectAnswerVoList +
                ", id=" + id +
                ", status=" + status +
                ", version=" + version +
                ", remark='" + remark + '\'' +
                '}';
    }
}
