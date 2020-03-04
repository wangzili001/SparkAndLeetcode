package leetcode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class TwoNumberToAdd {
    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.append(new Node(4));
        node1.append(new Node(3));

        Node node2 = new Node(5);
        node2.append(new Node(6));
        node2.append(new Node(4));


    }
    public  Node addTwoNumbers(Node l1, Node l2) {
        //定义三个变量
        int sum;//定义相加数
        int flag=0;//若两数相加大于10则像前加1
        Node pre = null;
        while (l1.next!=null||l2.next!=null){
            sum = l1.data + l2.data;

        }
        return null;
    }
}

