package exam.demo.server.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author luohui
 */
public class RandomTask {
    private List<Integer> data;
    private int n;
    private int sum;
    private int[] book;
    private List<Integer> result;
    private ThreadLocalRandom random = ThreadLocalRandom.current();

    public RandomTask(int n, List<Integer> idList) {
        this.n = n;
        sum = idList.size();
        book = new int[sum];
        data = new ArrayList<>(idList.size());
        for (int i = 0; i < sum; ) {
            data.add(idList.get(i++));
        }
        result = new ArrayList<>(n);
    }

    public List<Integer> gen() {
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(sum);
            //如果已取出，则需要再生成一个不重复的
            while (book[index] == 1) {
                index = random.nextInt(sum);
            }
            //将其标记为取出状态
            book[index] = 1;
            result.add(data.get(index));
        }
        return result;
    }

}
