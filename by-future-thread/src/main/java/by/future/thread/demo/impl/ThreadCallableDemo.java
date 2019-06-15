package by.future.thread.demo.impl;


import java.util.concurrent.Callable;

/**
 * @Author：by@Deng
 * @Date：2019/6/15 23:52
 */
public class ThreadCallableDemo implements Callable {

    private int i;

    public ThreadCallableDemo() {}

    public ThreadCallableDemo(int i) {
        this.i = i;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        Integer j = i<<1;
        return j;
    }
}
