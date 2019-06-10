package 笔记.pojo;

import javax.validation.constraints.NotNull;

/**
 * @ClassName BinaryTree
 * @Description 二叉树
 * @Author shnstt
 * @Date 2019/6/17 12:32
 * @Version 1.0
 **/
public class BinaryTree {
    /**
     * 树高度
     */
    private int hight;
    /**
     * 根节点
     */
    private BinaryNode root;

    public BinaryTree(@NotNull BinaryNode root) {
        this.root = root;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(BinaryNode root) {
        this.root = root;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }
}
