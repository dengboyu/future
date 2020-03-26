package by.future.servicebiz.thread.demo.impl;


import java.util.concurrent.Callable;

/**
 * @Author：by@Deng
 * @Date：2019/6/15 23:52
 */
public class ThreadCallableDemo implements Callable<String> {

    private int i;

    public ThreadCallableDemo(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(4000);
        return String.format("异步返回的值:%s",i);
    }
}
