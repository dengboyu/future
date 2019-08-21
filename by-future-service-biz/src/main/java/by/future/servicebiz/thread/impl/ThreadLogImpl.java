package by.future.servicebiz.thread.impl;

import by.future.servicebiz.factory.LogFactory;
import by.future.servicebiz.multipleinstance.ILog;

/**
 * @author by@Deng
 * @create 2019-08-21 22:57
 */
public class ThreadLogImpl implements Runnable {

    private String name;


    public ThreadLogImpl(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(500);

            ILog log = LogFactory.builderLog(name);
            System.out.println("log的地址:"+log);
            log.info();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
