package by.future.servicebiz.thread.demo.impl;

import by.future.common.pattern.singleton.PersonSingle;

/**
 * @author by@Deng
 * @create 2019-08-21 00:42
 */
public class PersonSingleThread implements Runnable {

    @Override
    public void run() {

        try {
            Thread.currentThread().sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (PersonSingleThread.class){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PersonSingle personSingle = PersonSingle.getInstance();
            System.out.println("获取到："+personSingle);
        }
    }
}
