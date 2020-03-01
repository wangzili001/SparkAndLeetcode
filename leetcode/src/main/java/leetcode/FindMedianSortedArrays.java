package leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] int1 = new int[]{1,2};
        int[] int2 = new int[]{3,4};
        new FindMedianSortedArrays().findMedianSortedArrays(int1,int2);
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0.0f;

        return result;
    }
}
