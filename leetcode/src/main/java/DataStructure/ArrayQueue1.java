package DataStructure;

import java.util.Arrays;

/**
 * 单向 队列
 */
public class ArrayQueue1 {
    private int MAXSIZE;
    private int[] ints;
    private int front;//指向队列头
    private int rear;//指向队列尾

    public ArrayQueue1(int MAXSIZE) {
        this.MAXSIZE = MAXSIZE;
        ints = new int[MAXSIZE];
        front=-1;
        rear=-1;
    }
    //TODO 判断是否为空  若头尾相同 则为空
    public Boolean isEmpty(){
        return rear==front;
    }
    //TODO 判断是否为满 若尾和最大数相同
    public Boolean isFull(){
        return MAXSIZE == rear+1;
    }
    //TODO 增加元素 返回是否成功
    public Boolean add(int i){
        if(isFull()){
            System.out.println("该队列已经满了，清稍等...");
            return false;
        }
        rear++;
        ints[rear] = i;
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
