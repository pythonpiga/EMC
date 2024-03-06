package com.broad.emc.module.controller;

import com.broad.emc.module.entity.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emc/treeNode")
@Slf4j
public class TreeData {
    /**
     * 单个父节点模式
     *
     * @return
     */
    @GetMapping("/oneFather")
    public TreeNode listOne() {
        List<TreeNode> nodes = new ArrayList<>();
        // 封装数据：模拟数据数据
        // 注意：从数据库中查询到的数据没有childrenNode属性，那我们可以使用BeanUtils和mapstruct进行对象转换
        // 根子节点的pid指向父级的id
        Collections.addAll(nodes,
                //根节点
                TreeNode.builder().id(1).pid(0).bmmc("江西省").build(),   
                    //根子节点1 children的pid=父级的id
                    TreeNode.builder().id(2).pid(1).bmmc("吉安市").build(),
                        TreeNode.builder().id(3).pid(2).bmmc("111").build(),
                        TreeNode.builder().id(4).pid(2).bmmc("22").build(),
                        TreeNode.builder().id(5).pid(2).bmmc("33").build(),
                            TreeNode.builder().id(6).pid(5).bmmc("章贡").build()
    
//                    //根节点2
//                    TreeNode.builder().id(7).pid(1).bmmc("遂川县").build(),
//                    //根节点3
//                    TreeNode.builder().id(8).pid(1).bmmc("赣州市").build()
        );

        Collections.addAll(nodes,
                
                //根节点2
                TreeNode.builder().id(7).pid(1).bmmc("遂川县").build(),
                //根节点3
                TreeNode.builder().id(8).pid(1).bmmc("赣州市").build()
        );
        // 获取树节点
        TreeNode root = nodes.stream().filter(node -> Objects.equals(node.getPid(), 0)).findFirst().orElse(null);
        if (root == null) {
            return null;
        }
        root.setChildren(getChildren(root, nodes));
        return root;
    }

    
    /**
     * 递归获取根节点的子节点信息
     *
     * @param root  根节点
     * @param nodes 要遍历的节点
     * @return
     */
    private List<TreeNode> getChildren(TreeNode root, List<TreeNode> nodes) {
        List<TreeNode> childrenNodes = nodes.stream()
                // 使用stream流过滤出数据的父级id信息等于根节点的id信息的数据
                .filter(node -> Objects.equals(node.getPid(), root.getId()))
                .map(childNode -> {
                    // 同时使用map方法对每个过滤后的数据进行处理，递归找到其对应的子数据
                    childNode.setChildren(getChildren(childNode, nodes));
                    return childNode;
                })
                .collect(Collectors.toList());
        return childrenNodes;
    }
}