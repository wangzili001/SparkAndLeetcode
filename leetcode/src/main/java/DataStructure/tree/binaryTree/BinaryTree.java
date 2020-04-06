package DataStructure.tree.binaryTree;

import lombok.Setter;

/**
 * 二叉树
 */
public class BinaryTree {
    @Setter
    private HeroNode root;
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }
    //前序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }
    //前序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }
}
