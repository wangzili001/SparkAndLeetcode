package DataStructure.search;

import org.junit.Test;

import java.util.List;

public class SearchDriver {
    int[] arr1 = {1,2,3,4,5,6,7,8,9,11,11,11,13,14,14,14,14,16,18};
    @Test
    public void BinarySearch(){
        BinarySearch binarySearch = new BinarySearch();
        List list = binarySearch.binarySearchArr(arr1, 11, 0, arr1.length - 1);
        System.out.println(list);
    }
}
