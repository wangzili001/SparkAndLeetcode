package leetcode;

import java.util.Arrays;

/**
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 *
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class MergeArray {
    public static void main(String[] args) {
        int[] A = new int[]{4,7,9,0,0,0};
        int[] B = new int[]{2,5,6};
        new MergeArray().merge(A,3,B,3);
        System.out.println(Arrays.toString(A));
    }
    public void merge(int[] A, int m, int[] B, int n) {
        //双指针做法
        int end = m+n-1;//指向数组组合后最后一个
        int Aend = m-1;//指向A数组最后一个
        int Bend = n-1;//指向A数组最后一个
        //从后往前放
        while (Bend >= 0&&Aend>=0){
            if(A[Aend]<=B[Bend]){
                A[end--] = B[Bend--];
            }else {
                A[end--]=A[Aend--];
            }
        }
        //若Aend部位-1 Bend为-1 因为Aend为有序 则合并数据已经排号
        //如果Bend没有到-1 则继续循环
        while (Bend>=0){
            A[Bend] = B[Bend--];
        }
    }
}
