package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class SumOfThree {
    public static void main(String[] args) {
        int[] ints = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = new SumOfThree().threeSum(ints);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        //{-4,-1,-1,0,1,2}
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len ; i++) {
            // 如果当前数字大于0，直接结束
            if(nums[i] > 0) break;
            //出现重复元素 打断当前循环
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int Left = i+1;
            int Right = len-1;
            while(Left < Right){
                int sum = nums[i] + nums[Left] + nums[Right];
                if(sum == 0){
                    lists.add(Arrays.asList(nums[i],nums[Left],nums[Right]));
                    while (Left<Right && nums[Left] == nums[Left+1]) Left++; // 去重
                    while (Left<Right && nums[Right] == nums[Right-1]) Right--; // 去重
                    Left++;
                    Right--;
                }
                else if (sum < 0) Left++;
                else if (sum > 0) Right--;
            }
        }
        return lists;
    }
}
