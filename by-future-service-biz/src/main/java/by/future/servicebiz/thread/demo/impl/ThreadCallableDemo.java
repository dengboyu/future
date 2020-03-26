package by.future.servicebiz.thread.demo.impl;


import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * @Author：by@Deng
 * @Date：2019/6/15 23:52
 */
public class ThreadCallableDemo implements Callable<String>, Supplier<String> {

    private int i;

    public ThreadCallableDemo(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {

//        int randomInt = 1000 + new Random().nextInt(1500);    //IO型
        int randomInt =  new Random().nextInt(100);  //cpu型

        Thread.sleep(randomInt);
//        System.out.println(String.format("异步返回的值:%s",i));
        return String.format("异步返回的值:%s，随机值：%s",i,randomInt);
    }


    @Override
    public String get() {

        int randomInt =new Random().nextInt(2000);

        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(String.format("异步返回的值:%s",i));
        return String.format("异步返回的值:%s，随机值：%s",i,randomInt);
    }
}
