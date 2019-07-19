package by.future.web.test.springboot;


import by.future.common.cache.SafeBuffer;
import by.future.common.utils.ThreadUtils;
import by.future.thread.demo.impl.ThreadDemo;
import by.future.thread.demo.impl.ThreadLocalTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @Author：by@Deng
 * @Date：2019/3/23 11:44
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ThreadTest {


    @Test
    public void test(){

        SafeBuffer<String> safeBuffer = new SafeBuffer();

        for(int i=0;i<10;i++){
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.currentThread().sleep(1000000);

                        safeBuffer.put("hi,我是生产者:");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread1.start();
        }

        for(int i=0;i<20;i++){
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        String data = safeBuffer.get();

                        System.out.println("线程：" +Thread.currentThread().getName()+"获取到的数据是："+data);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread2.start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testThread(){

        ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        List<Future> futureList = new ArrayList<>();
        for(int i=0;i<20;i++){

            executor.execute(new ThreadDemo());

//            Future<Integer> f= executor.submit(new ThreadCallableDemo(i));
//            futureList.add(f);
        }

        /*futureList.stream().forEach(n->{
            try {

                System.out.println(n.get());

            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/

        System.out.println("看看谁先出来");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThreadLocal() throws InterruptedException {

        /*ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        for(int i=0;i<40;i++){
            executor.execute(new ThreadLocalTest());
        }*/

        /*Thread thread1 = new Thread(new ThreadLocalTest());
        Thread thread2 = new Thread(new ThreadLocalTest());

        thread1.start();
        thread2.start();*/


//        Thread.sleep(5000);

    }



}
