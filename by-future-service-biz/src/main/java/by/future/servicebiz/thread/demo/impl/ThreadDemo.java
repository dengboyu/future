package by.future.servicebiz.thread.demo.impl;

/**
 * 线程测试类
 *
 * @author by@Deng
 * @create 2018-09-08 20:26
 */
public class ThreadDemo implements Runnable {

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
