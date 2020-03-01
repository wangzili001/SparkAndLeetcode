package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用队列实现栈的下列操作：
 *
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 */
public class QueueToStack {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int i =2;
        Integer remove = list.remove(i--);
        System.out.println(remove);
        System.out.println(i);
        System.out.println(list);
    }
}
class MyStack {
    List<Integer> list = null;
    int rear;//指向最后一个入栈索引

    /** Initialize your data structure here. */
    public MyStack() {
        list = new ArrayList<>();
        rear = -1;
    }

    /** Push element x onto stack. */
    public void push(int x) {
        list.add(x);
        rear++;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return list.remove(rear--);
    }

    /** Get the top element. */
    public int top() {
        return list.get(rear);
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return -1 == rear;
    }
}
