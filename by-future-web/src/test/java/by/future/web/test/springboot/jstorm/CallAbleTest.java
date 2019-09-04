package by.future.web.test.springboot.jstorm;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author by@Deng
 * @create 2019-09-05 02:15
 */
public class CallAbleTest implements Callable {

    private int count;

    public CallAbleTest(int count) {
        this.count = count;
    }

    @Override
    public Object call() throws Exception {

        System.out.println("当前线程名称："+Thread.currentThread().getName());

        TimeUnit.SECONDS.sleep(2);

        return count;
    }
}
