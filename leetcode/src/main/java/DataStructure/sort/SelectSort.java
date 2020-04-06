package DataStructure.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public int[] selectSort(int[] initArr) {
        int min, index;
        for (int i = 0; i < initArr.length; i++) {
            min = 0;
            index = i;
            for (int j = i; j < initArr.length; j++) {
                if(i == j) min = initArr[j];
                if (min > initArr[j]) {
                    min = initArr[j];
                    index = j;
                }
            }
            initArr[index] = initArr[i];
            initArr[i] = min;
//            System.out.println("第" + (i+1) + "次排序:" + Arrays.toString(initArr));
        }
        return initArr;
    }
}
