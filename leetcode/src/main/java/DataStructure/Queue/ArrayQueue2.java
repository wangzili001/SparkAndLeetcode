package DataStructure.Queue;

import java.util.Arrays;

/**
 * 环形 队列
 */
public class ArrayQueue2 {
    private int MAXSIZE;
    private int[] ints;
    private int front;//指向队列的第一个元素
    private int rear;//指向队列的最后一个元素的后一个位置 空出一个空间作为约定

    public ArrayQueue2(int MAXSIZE) {
        this.MAXSIZE = MAXSIZE;
        ints = new int[MAXSIZE];
        front=0;
        rear=0;
    }
    //TODO 判断是否为空  若头尾相同 则为空
    public Boolean isEmpty(){
        return rear == front;
}
    //TODO 判断是否为满 若尾和最大数相同
    public Boolean isFull(){
        return (rear+1)%MAXSIZE == front;
    }
    //TODO 增加元素 返回是否成功
    public Boolean add(int data){
        if(isFull()){
            System.out.println("当前队列已满。。。");
            return false;
        }
        rear++;
        System.out.println(rear%MAXSIZE);
        ints[rear%MAXSIZE] = data;
        return true;
    }
    //TODO 取出元素 返回所需要的值
    public Integer get(){
        if(isEmpty()) {
            System.out.println("该队列为空了哦...");
            return null;
        }
        return ints[++front];
    }
    public void print(){
        if(isEmpty()){
            System.out.println("[]");
        }else {
            System.out.println(Arrays.toString(ints));
        }
    }
}
