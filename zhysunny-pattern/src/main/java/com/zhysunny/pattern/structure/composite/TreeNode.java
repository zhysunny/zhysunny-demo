package com.zhysunny.pattern.structure.composite;

import java.util.Vector;

/**
 * @author 章云
 * @date 2019/11/26 16:50
 */
public class TreeNode {

    private String name;
    private TreeNode parent;
    private Vector<TreeNode> children = new Vector<TreeNode>();

    public TreeNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public Vector<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Vector<TreeNode> children) {
        this.children = children;
    }

    /**
     * 添加子节点
     * @param node
     */
    public void add(TreeNode node) {
        children.add(node);
    }

    /**
     * 删除孩子节点
     * @param node
     */
    public void remove(TreeNode node) {
        children.remove(node);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
        "name='" + name + '\'' +
        ", parent=" + parent +
        ", children=" + children +
        '}';
    }

}
