package leetcode;

import java.util.Arrays;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 *
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class SolutionApp {

    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }
    public static boolean isAnagram(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return  Arrays.equals(str1,str2);
    }
    /**
     * 解法1
     * @param s
     * @param t
     * @return
     */
    /*public static boolean isAnagram(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        for (char c : t.toCharArray()) {
            if(map.containsKey(c)){
                map.put(c,map.get(c)-1);
            }else {
                return false;
            }
        }
        for (Character character : map.keySet()) {
            if(map.get(character) != 0){
                return false;
            }
        }
        return true;
    }*/
}
