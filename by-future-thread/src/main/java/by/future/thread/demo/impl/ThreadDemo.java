package by.future.thread.demo.impl;

import by.future.common.utils.GetBeanFromApplicationContextUtils;
import by.future.thread.demo.IThreadTwo;
import by.future.thread.entity.ThreadTestEntity;
import by.future.thread.entity.ThreadTestTwoEntity;

/**
 * 线程测试类
 *
 * @author by@Deng
 * @create 2018-09-08 20:26
 */
public class ThreadDemo implements Runnable {

    private static IThreadTwo threadTwo = GetBeanFromApplicationContextUtils.getBean("threadTwo", IThreadTwo.class);
    private static IThreadTwo threadTwo1 = GetBeanFromApplicationContextUtils.getBean("threadThree", IThreadTwo.class);

    private ThreadTestEntity threadTestEntity;
    private ThreadTestTwoEntity threadTestTwoEntity;

    public ThreadDemo() {
    }

    public ThreadDemo(ThreadTestEntity threadTestEntity, ThreadTestTwoEntity threadTestTwoEntity) {
        this.threadTestEntity = threadTestEntity;
        this.threadTestTwoEntity = threadTestTwoEntity;
    }

    @Override
    public void run() {
//        System.out.println(threadTestEntity.getName()+"------"+threadTestEntity.getAge());
//        System.out.println(threadTestTwoEntity.getAddress()+"==="+threadTestTwoEntity.getStu());
        threadTwo.test();
        threadTwo1.test();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
