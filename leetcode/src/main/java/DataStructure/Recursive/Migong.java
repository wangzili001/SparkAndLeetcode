package DataStructure.Recursive;

import java.util.Arrays;

public class Migong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部标识1
        for (int i=0;i<map.length;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        Boolean aBoolean = setWay(map, 1, 1);
        ArrayPrint(map);
    }


    //使用递归给小球找路
    //说明
    //map表示地图 ij表示从哪里出发（i，j）
    //如果小球到map[6][5] 则说明通路找到
    //约定 当map[i][j]为0 表示该点没有走过 当是1的时候表示为墙 2表示走过 3表示该位置已经走过 但是走不通
    //在走之前 需要确定一个策略 下-》右-》上-》左 如果走不通 再回溯
    /**
     * @param map 表示地图
     * @param i 初始横坐标
     * @param j 初始纵坐标
     * @return 如果找到通路则返回true 否则false
     */
    public static Boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }
        if(map[i][j] == 0){//如果当前这个点没走过
            //按照策略 下右上左
            map[i][j] = 2;//假定该点是可以走通的
            if(setWay(map,i+1,j)){//向下走
                return true;
            }else if(setWay(map,i,j+1)){//向右走
                return true;
            }else if(setWay(map,i-1,j)){//向上奏
                return true;
            }else if(setWay(map,i,j-1)){//向左走
                return true;
            }else {
                //说明该点是走不通的
                map[i][j] = 3;
                return false;
            }
        }else {
            return false;
        }
    }

    public static void ArrayPrint(int[][] ints){
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }
}
