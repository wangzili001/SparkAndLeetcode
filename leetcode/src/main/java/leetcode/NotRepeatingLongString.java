package leetcode;


/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class NotRepeatingLongString {
    public static void main(String[] args) {
//        int a = new leetcode.NotRepeatingLongString().lengthOfLongestSubstring("abcabcbb");
        String s = "avca";
        System.out.println(s.indexOf("a",1));
    }

    public int lengthOfLongestSubstring(String s) {
        int i=0,flag=0,result=0,length=0;
        while (i<s.length()){
            int pos = s.indexOf(s.charAt(i),flag);
            if(pos<i){
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return result;
    }
}
