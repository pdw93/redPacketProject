package 笔记.pojo;


/**
 * @ClassName BinaryTreeUtils
 * @Description 简单二叉树工具类
 * @Author shnstt
 * @Date 2019/6/17 12:36
 * @Version 1.0
 **/
public class BinaryTreeUtils {

    private BinaryTreeUtils() {
    }

    public static BinaryTree createBinaryTree(BinaryNode root) {
        return new BinaryTree(root);
    }

    public static int heightValue(BinaryTree tree) {
        return tree.getHight();
    }

    /**
     * 此节点作为根节点的树的高度
     *
     * @param note
     * @return
     */
    public static int heightValue(@javax.validation.constraints.NotNull BinaryNode note) {
        return heightValue(note, 1);
    }

    // 指定当前节点初始高度
    private static int heightValue(BinaryNode note, int currentHeight) {
        if (note.getLeft() == null && note.getRight() == null) {
            return currentHeight;
        } else {
            int leftHeight = note.getLeft() != null ? heightValue(note.getLeft(), currentHeight + 1) : currentHeight;
            int rightHeight = note.getRight() != null ? heightValue(note.getRight(), currentHeight + 1) : currentHeight;
            return leftHeight >= rightHeight ? leftHeight : rightHeight;
        }
    }
}
