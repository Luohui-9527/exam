package exam.demo.modulebaseinfo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 深度优先搜索工具类
 * 用于遍历树结构，如分类树、组织结构树等
 *
 * @param <T> 节点类型
 * @author luohui
 */
public class DFSUtil<T> {
    private List<String> res = new ArrayList<>();
    private Function<String, List<T>> getChildrenFunction;

    /**
     * 构造函数
     *
     * @param rootId              根节点ID
     * @param getChildrenFunction 获取子节点的函数
     */
    public DFSUtil(String rootId, Function<String, List<T>> getChildrenFunction) {
        this.getChildrenFunction = getChildrenFunction;
        res.add(rootId);
        dfs(rootId);
    }

    /**
     * 深度优先搜索
     *
     * @param currentId 当前节点ID
     */
    private void dfs(String currentId) {
        List<T> children = getChildrenFunction.apply(currentId);
        if (children == null || children.isEmpty()) {
            return;
        }
        // 假设每个节点都有getId()方法获取ID
        List<String> idList = new ArrayList<>();
        for (T child : children) {
            try {
                // 使用反射获取id属性
                String id = (String) child.getClass().getMethod("getId").invoke(child);
                idList.add(id);
                res.add(id);
            } catch (Exception e) {
                // 忽略反射异常
            }
        }
        for (String id : idList) {
            dfs(id);
        }
    }

    /**
     * 获取所有节点ID列表
     *
     * @return 节点ID列表
     */
    public List<String> getRes() {
        return res;
    }
}