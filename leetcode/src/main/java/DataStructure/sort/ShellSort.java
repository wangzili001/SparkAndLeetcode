package DataStructure.sort;

import java.util.Arrays;

public class ShellSort {
    //交换法
    public void shellSort1(int[] arr){
        int temp;
        int z=0;
        for(int step = arr.length/2;step>0;step /= 2){
            temp = 0;
            for (int i=step;i<arr.length;i++){
                for (int j=i-step;j>=0;j-=step){
                    if(arr[j] >arr[i]){
                        temp = arr[j];
                        arr[j] = arr[i];
                        arr[i] = temp;
                    }
                }
            }
            z++;
            System.out.println("第"+z+"次排序"+ Arrays.toString(arr));
        }
    }
    //对交换法改进  移位法
    public void shellSort2(int[] arr){
        int temp;
        int z=0;
        for(int step = arr.length/2;step>0;step /= 2){
            for (int i=step;i<arr.length;i++){
                int j = i;
                temp = arr[j];
                if(arr[j]<arr[j-step]){
                    while (j-step>=0&&temp<arr[j-step]){
                        //移动
                        arr[j] = arr[j-step];
                        j -= step;
                    }
                    //当退出循环后 说明temp找到插入的位置了
                    arr[j] = temp;
                }
            }
            z++;
            System.out.println("第"+z+"次排序"+ Arrays.toString(arr));
        }
    }
}
