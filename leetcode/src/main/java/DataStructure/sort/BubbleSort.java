package DataStructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序  时间复杂度O(n^2) 大的数往最后排
 */
public class BubbleSort {
    public int[] bubbleSort(int[] initArr){
        int tmp = 0;
        for (int i = 0; i < initArr.length; i++) {
            for (int j=0;j<initArr.length-i-1;j++){
                if(initArr[j]>initArr[j+1]){
                    tmp = initArr[j];
                    initArr[j] = initArr[j+1];
                    initArr[j+1] = tmp;
                }
            }
//            System.out.println("第"+i+"排序:"+ Arrays.toString(initArr));
        }
        return initArr;
    }
}
