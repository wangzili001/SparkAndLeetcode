package leetcode;

import java.util.Stack;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class SingleList {
    public static void main(String[] args) {
        Node node = new Node(1);
        node.append(new Node(2));
        node.append(new Node(3));
        node.append(new Node(4));
        node.append(new Node(5));
        node.show();
        Node node1 = reverseList(node);
        node1.show();
    }

    public static Node reverseList(Node head) {
        //解题1
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}