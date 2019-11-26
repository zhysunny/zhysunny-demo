package com.zhysunny.pattern.structure.composite;

/**
 * @author 章云
 * @date 2019/11/26 16:52
 */
public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.add(nodeC);
        root.add(nodeB);
        System.out.println(root);
        System.out.println("build the tree finished!");
    }

}
