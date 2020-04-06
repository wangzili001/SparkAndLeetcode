package DataStructure.sort;

import java.util.Arrays;

/**
 * 插入排序  时间复杂度O(n^2)
 */
public class InsertSort {
    public int[] insertSort(int[] initArr){
        for(int i=1;i<initArr.length;i++){
            //定义待插入数
            int insertVal = initArr[i];
            int insertIndex = i-1;

            while (insertIndex>=0&&insertVal<initArr[insertIndex]){
                initArr[insertIndex+1] = initArr[insertIndex];
                insertIndex--;
            }
            initArr[insertIndex+1] = insertVal;
//            System.out.println("第"+i+"次排序："+ Arrays.toString(initArr));
        }

        return initArr;
    }
}
