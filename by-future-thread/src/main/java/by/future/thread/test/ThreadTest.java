package by.future.thread.test;

import by.future.thread.entity.ThreadTestEntity;
import by.future.thread.entity.ThreadTestTwoEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 线程测试类
 *
 * @author by@Deng
 * @create 2018-09-08 20:26
 */
@Component
@Scope("prototype")
public class ThreadTest implements Runnable {

    @Resource
    private ThreadTwo threadTwo;

    private ThreadTestEntity threadTestEntity;
    private ThreadTestTwoEntity threadTestTwoEntity;

    public ThreadTest() {
    }

    public ThreadTest(ThreadTestEntity threadTestEntity, ThreadTestTwoEntity threadTestTwoEntity) {
        this.threadTestEntity = threadTestEntity;
        this.threadTestTwoEntity = threadTestTwoEntity;
    }

    @Override
    public void run() {
        System.out.println(threadTestEntity.getName()+"------"+threadTestEntity.getAge());
        System.out.println(threadTestTwoEntity.getAddress()+"==="+threadTestTwoEntity.getStu());
        threadTwo.test();
    }




}
