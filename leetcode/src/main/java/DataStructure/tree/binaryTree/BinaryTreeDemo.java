package DataStructure.tree.binaryTree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "公孙胜");
        //说明 先手动创建该二叉树 后续使用递归创建二叉树
        node1.setLeft(node2);
        node2.setLeft(node5);
        node1.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(node1);
        //测试 前序遍历
        System.out.println("前序遍历:");
        binaryTree.preOrder();
        //测试 中序遍历
        System.out.println("中序遍历:");
        binaryTree.infixOrder();
        //测试 后序遍历
        System.out.println("后序遍历:");
        binaryTree.postOrder();
    }
}
