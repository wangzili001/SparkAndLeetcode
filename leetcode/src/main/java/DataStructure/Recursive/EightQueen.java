package DataStructure.Recursive;

import java.util.Arrays;

public class EightQueen {
    //定义一个max表示共有多少个皇后
    int max = 8;
    public static int sum = 0;
    //定义一个数组array 保存皇后放置的位置 比如{0.4.7.5.2.6.1.3}
    int[] array = new int[max];

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.printf("一共有"+sum);
    }
    //编写一个方法 放置第n个皇后
    private void check(int n){
        if(n == max){
            System.out.println(Arrays.toString(array));
            sum++;
            return;
        }
        //依次放入8个皇后
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n 放到该行的第1列
            array[n] = i;
            //判断是否冲突
            if(IsSuccess(n)){
                check(n+1);
            }
        }
    }

    /**
     * 传过来一个坐标 判断是否成立
     * @param n 第n个皇后
     * @return
     */
    private Boolean IsSuccess(int n){
        //只用判断x轴和斜对角是否存在皇后
        for (int i = 0; i < n; i++) {
            //若array[i] == array[n] 则在相同列
            //若Math.abs(n-i)==Math.abs(array[n]-array[i]) 则表示判断第n个皇后是否和第i个皇后是否在同一斜线
            if((array[i] == array[n])||(Math.abs(n-i)==Math.abs(array[n]-array[i]))){
                return false;
            }
        }
        return true;
    }
}
