package DataStructure;


import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            mean();
            String select = sc.next();
            switch (select) {
                case "a":
                    break;
                case "g":
                    break;
                case "p":
                    break;
                case "f":
                    break;
                case "e":
                    System.exit(0);
                    break;

            }
        }
    }

    public static void mean() {
        System.out.println("=======================================");
        System.out.println("==========a:增加元素====================");
        System.out.println("==========g:得到元素====================");
        System.out.println("==========p:打印队列====================");
        System.out.println("==========f:是否为空====================");
        System.out.println("==========e:退    出====================");
        System.out.println("=======================================");
    }
}

