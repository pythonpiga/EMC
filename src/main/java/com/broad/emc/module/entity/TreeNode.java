package com.broad.emc.module.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeNode implements Serializable {
    private Integer id;
    private String bmmc;
    private Integer pid;
    // children属性用于保存当前记录的子节点信息
    // 注意：数据中对应的实体没有该字段，我们可以从数据库中查询所有记录后，再使用BeanUtils或MapStruct相关工具进行转换
    private List<TreeNode> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return Objects.equals(id, treeNode.id) && Objects.equals(bmmc, treeNode.bmmc) && Objects.equals(pid, treeNode.pid) && Objects.equals(children, treeNode.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bmmc, pid, children);
    }
}