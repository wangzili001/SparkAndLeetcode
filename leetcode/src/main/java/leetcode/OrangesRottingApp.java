package leetcode;

import java.util.*;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1
 * 示例 1：
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *  
 *
 * 提示：
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotting-oranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class OrangesRottingApp {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2,1,1},{1,1,0},{0,1,1}};
        new OrangesRottingApp().orangesRotting(grid);
    }
    public int orangesRotting(int[][] grid) {
        if(grid == null) return -1;
        boolean good = false; //这个变量判断0时刻是否还有新鲜的橘子
        Deque<int[]> dq = new LinkedList<>();
        int n = grid.length, m = grid[0].length;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++){
                //0时刻腐烂的橘子入队
                if(grid[i][j] == 2) dq.addLast(new int[]{i, j});
                if(grid[i][j] == 1) good = true;
            }
        if(!good) return 0;
        int time = -1;
        while(dq.size() != 0){
            time++;
            int size = dq.size();
            //  这个时刻所有腐烂的橘子都污染四周后，才开始下一分钟
            for(int i = 0; i < size; i++){
                int[] orange = dq.pollFirst();
                int x = orange[0], y = orange[1];
                // 判断四周的算法用官方的思路更加简洁，我这里写的很差
                if(x - 1 >= 0 && grid[x-1][y] == 1){
                    dq.addLast(new int[]{x - 1, y});
                    grid[x-1][y] = 2;
                }
                if(x + 1 < n && grid[x+1][y] == 1){
                    dq.addLast(new int[]{x + 1, y});
                    grid[x+1][y] = 2;
                }
                if(y - 1 >= 0 && grid[x][y-1] == 1){
                    dq.addLast(new int[]{x, y - 1});
                    grid[x][y-1] = 2;
                }
                if(y + 1 < m && grid[x][y+1] == 1){
                    dq.addLast(new int[]{x, y + 1});
                    grid[x][y+1] = 2;
                }
            }
        }
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(grid[i][j] == 1) return -1;
        return time;
    }
}
