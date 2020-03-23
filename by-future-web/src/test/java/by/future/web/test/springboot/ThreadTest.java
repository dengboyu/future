package by.future.web.test.springboot;


import by.future.common.cache.SafeBuffer;
import by.future.common.utils.ThreadUtils;
import by.future.servicebiz.thread.demo.impl.ThreadCallableDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author：by@Deng
 * @Date：2019/3/23 11:44
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ThreadTest {

    /**
     * 多线程qps的计算问题
     *
     * @Author: by@Deng
     * @Date: 2019/9/1 10:42 下午
     */
    @Test
    public void testConcurrent() throws Exception{

        Semaphore semaphore = new Semaphore(10);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for(int i = 0;i<1000;i++){
            int a = i;
            executorService.execute(()->{

                try {

                    semaphore.acquire();
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            });
        }

        Thread.sleep(20000);
    }


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
        for(int i=0;i<200;i++){

            Future<Object> f= executor.submit(new ThreadCallableDemo(i));
            //此处可以扩展监听机制 Future.addListener()方法

            futureList.add(f);
        }

        System.out.println("看看谁先出来");

        for(int i=0;i<futureList.size();i++){
            try {
                System.out.println(futureList.get(i).get(5,TimeUnit.SECONDS));    //按照请求的顺序返回来，future的get()是阻塞调用线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        System.out.println("再走试试");

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }

    @Test
    public void testThreadLocal() throws InterruptedException {

        int max = 20;

        CountDownLatch countDownLatch = new CountDownLatch(max);
        ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        for(int i=0;i<max;i++){
            final int a = i;
            executor.execute(()-> {
                try {

                    countDownLatch.countDown();

                    System.out.println("遇见最好的自己:"+a+",countDown数："+countDownLatch.getCount());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        countDownLatch.await();
        System.out.println("最终");

        Thread.sleep(5000);

    }




    @Test
    public void testOther() throws InterruptedException {

        ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        CountDownLatch countDownLatch = new CountDownLatch(100);

        for(int i =0;i<100;i++){
            executor.execute(new RunTest(countDownLatch,i));
        }

//        System.out.println("剩余："+countDownLatch.getCount());
        countDownLatch.await();
        System.out.println("结束");

        Thread.sleep(10000);


    }

    class RunTest implements Runnable{

        private CountDownLatch countDownLatch;
        private int i;

        private RunTest(CountDownLatch countDownLatch, int i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {

            System.out.println("循环前："+i+"---"+countDownLatch.getCount());

            countDownLatch.countDown();

//            try {
//
//                Thread.sleep(5000);
//
//                countDownLatch.await();
//                System.out.println("统一调用"+i);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//
//            }
        }
    }


    volatile  AtomicInteger num;
    @Test
    public void testAtomic(){

        num = new AtomicInteger(0);

        ExecutorService executorService = ThreadUtils.getExecutorServiceInstance();

        for(int i=0;i<100;i++){

            executorService.submit(()->{

                num.incrementAndGet();

            });

        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num);

    }


}
