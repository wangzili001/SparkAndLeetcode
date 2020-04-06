package DataStructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 必须是有序数组
 */
public class BinarySearch  {
    /**
     * 单一返回
     * @param arr 查找的数组
     * @param num 需要查找的数
     * @return 返回索引 若未找到返回-1
     */
    public int binarySearch(int[] arr,int num,int left,int right){
        int mid = (left+right)/2;
        int midval = arr[mid];
        if(left>right){
            return -1;
        }
        if(num>midval){
            return binarySearch(arr,num,mid+1,right);
        }else if(num<midval){
            return binarySearch(arr,num,left,mid-1);
        }else {
            return mid;
        }
    }
    /**
     * {1,2,4,6,6,6,7,9}
     *  找到为6发元素索引
     *  思路 当找到元素后 不要直接返回 左右扫描
     */
    public List binarySearchArr(int[] arr, int num, int left, int right){
        int mid = (left+right)/2;
        int midval = arr[mid];
        if(left>right){
            return null;
        }
        if(num>midval){
           return binarySearchArr(arr,num,mid+1,right);
        }else if(num<midval){
           return binarySearchArr(arr,num,left,mid-1);
        }else {
            int leftTmp = mid - 1;
            int rightTmp = mid + 1;
            List<Integer> res = new ArrayList<>();
            //左扫描
            while (true){
                if(leftTmp<0||arr[leftTmp]!=num){
                    break;
                }
                res.add(leftTmp--);
            }
            res.add(mid);
            //右扫描
            while (true){
                if(rightTmp>=arr.length||arr[rightTmp]!=num){
                    break;
                }
                res.add(rightTmp++);
            }
            return res;
        }
    }
}
