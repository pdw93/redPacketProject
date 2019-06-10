package 笔记.pojo;

/**
 * @ClassName BinaryNode
 * @Description 二叉树结点
 * @Author shnstt
 * @Date 2019/6/17 12:28
 * @Version 1.0
 **/
public class BinaryNode {
    /**
     * 节点值
     */
    private int value;
    /**
     * 左子节点
     */
    private BinaryNode left;
    /**
     * 右子节点
     */
    private BinaryNode right;
    /**
     * 父节点
     */
    private BinaryNode parent;

    public BinaryNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
        left.setParent(this);
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
        right.setParent(this);
    }

    public BinaryNode getParent() {
        return parent;
    }

    public void setParent(BinaryNode parent) {
        this.parent = parent;
    }
}
