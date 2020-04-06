package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 *
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 *
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 *
 * 限制：
 *
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxQueueDriver {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        System.out.println(maxQueue.pop_front());
    }
}
class MaxQueue {

    Integer max_value = null;
    Queue<Integer> queue = null;

    public MaxQueue() {
        queue = new LinkedList<Integer>();
    }

    public int max_value() {
        return max_value==null?-1:max_value;
    }

    public void push_back(int value) {
        if(max_value!=null){
            if(max_value<value){
                max_value = value;
            }
        }else {
            max_value = value;
        }
        queue.add(value);
    }

    public int pop_front() {
        if(queue.size() == 0) return -1;
        Integer remove = queue.remove();
        Integer max = null;
        if(remove == max_value&&queue.size()!=0){
            for (Integer integer : queue) {
                if(max == null){
                    max = integer;
                }else {
                    if(max<integer){
                        max =integer;
                    }
                }
            }
            max_value = max;
        }
        if(queue.size() == 0){
            max_value = null;
        }
        return remove;
    }
}