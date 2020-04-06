package DataStructure.Recursive;

/**
 * 递归的使用
 * 阶乘
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
    public static  int factorial(int num){
       if(num == 1){
           return 1;
       }else {
           return factorial(num-1)*num;
       }
    }
}
