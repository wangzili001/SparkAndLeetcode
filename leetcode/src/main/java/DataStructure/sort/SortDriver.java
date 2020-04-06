package DataStructure.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * 排序驱动类
 */
public class SortDriver {
    int[] intArr = new int[]{6, 4, 7, 8, 3, 5, 2, 1, 9};
    //定义需要排序数组
    int[] RandomInt = new int[80000];
    static long start = 0l;
    static long end = 0l;

    @Before
    public void init() {
        for (int i = 0; i < RandomInt.length; i++) {
            RandomInt[i] = (int) (Math.random() * 80000);
        }
        start = System.currentTimeMillis();
    }

    @Test
    public void quickSort() {
        new QuickSort().quickSort(intArr, 0, intArr.length - 1);
        System.out.println(Arrays.toString(intArr));
    }

    @Test
    public void  ShellSort(){
//        new ShellSort().shellSort1(intArr);
        new ShellSort().shellSort2(intArr);
    }

    @Test
    public void  InsertSort(){
//        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(new InsertSort().insertSort(RandomInt)));
    }
    @Test
    public void bubbleSort() {
        //冒泡排序
//        System.out.println(Arrays.toString(new BubbleSort().bubbleSort(intArr)));
        System.out.println(Arrays.toString(new BubbleSort().bubbleSort(RandomInt)));
    }

    @Test
    public void selectSort() {
        //选择排序
//        System.out.println(Arrays.toString(new SelectSort().selectSort(intArr)));
        System.out.println(Arrays.toString(new SelectSort().selectSort(RandomInt)));
    }

    @After
    public void end() {
        end = System.currentTimeMillis();
        System.out.println("用时" + ((end - start) ) + "毫秒");
    }
}
