package DataStructure.stack;

import java.util.Stack;

public class ArrayStack {
    int[] i = null;
    int front;
    int first;

    public ArrayStack(int size) {
        i = new int[size];
        front = 0;
        first = 0;
    }
    //栈的数据大小
    public int size(){
        return front;
    }
    //是否为空
    public Boolean isEmpty(){
        return front==0;
    }
    //是否已满
    public Boolean isFull(){
        return front == i.length-1;
    }
    //存入元素
    public Boolean push(int num){
        if(isFull()){
            return false;
        }
        i[front++] = num;
        return true;
    }
    //弹出栈顶元素
    public int pop(){
        return i[front--];
    }
    //得到第一个元素
    public int getFirst(){
        return i[first];
    }
}
