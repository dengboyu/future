package by.future.web.test.springboot;


import by.future.common.cache.SafeBuffer;
import by.future.common.utils.CompletableFutureUtils;
import by.future.common.utils.ThreadUtils;
import by.future.servicebiz.thread.demo.impl.ThreadCallableDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

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

    private void testPool(int i){

        ThreadPoolExecutor executor = (ThreadPoolExecutor) ThreadUtils.getExecutorServiceInstance();

        Future<String> future= executor.submit(new ThreadCallableDemo(i));
        //此处可以扩展监听机制 Future.addListener()方法

        try {
            String value = future.get(2,TimeUnit.SECONDS);
            System.out.println(value);
        } catch (Exception e) {
            System.out.println("超时："+i);
        }

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(new ThreadCallableDemo(i),executor);

        CompletableFuture<String> newFuture = CompletableFutureUtils.timeoutAfter(future,2,TimeUnit.SECONDS);

        try {
            System.out.println(newFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    /**
     * FutureTask
     *
     * @Author：by@Deng
     * @Date：2020/3/26 16:38
     */
    @Test
    public void testThread(){

        int max = 50;
        CountDownLatch countDownLatch = new CountDownLatch(max);
        ExecutorService executor111 = Executors.newFixedThreadPool(max);
        for(int i=0;i<max;i++){
            final  int a =i;
            executor111.execute(()->{

                try {
                    countDownLatch.countDown();

                    countDownLatch.await();

                    testPool(a);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
        executor111.shutdown();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*ThreadPoolExecutor executor = (ThreadPoolExecutor) ThreadUtils.getExecutorServiceInstance();

        List<Future> futureList = new ArrayList<>();
        for(int i=0;i<20;i++){

            Future<String> f= executor.submit(new ThreadCallableDemo(i));
            //此处可以扩展监听机制 Future.addListener()方法

            futureList.add(f);
        }

        ExecutorService executor11 = Executors.newFixedThreadPool(10);

        long beginTime = System.currentTimeMillis();

        for(int i=futureList.size()-1;i>=0;i--){

            final int a =i;
            executor11.execute(()->{
                try {
                    System.out.println("队列数:"+executor.getQueue().size());
                    System.out.println(futureList.get(a).get(1,TimeUnit.SECONDS));    //按照请求的顺序返回来，future的get()是阻塞调用线程
                } catch (Exception e) {
                    System.out.println("我是"+a+"超时");
                }
            });
        }

        long endTime = System.currentTimeMillis();

        executor11.shutdown();

        System.out.println("遍历完用时："+(endTime-beginTime));

        threadBlock();
        System.out.println("sdfsdf");*/

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }


    /**
     * 是线程中断多长时间
     *
     * @Author：by@Deng
     * @Date：2020/3/24 13:58
     */
    private void threadBlock(){

        long time1 = System.currentTimeMillis();

        LockSupport.parkNanos(5000000000l);

        long time2 = System.currentTimeMillis();

        System.out.println("调用者线程挂了"+(time2-time1)+"时间");
    }


    /**
     * completableFuture
     *
     * @Author：by@Deng
     * @Date：2020/3/26 16:39
     */
    @Test
    public void testCompletableFuture(){

        ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        ArrayList<Future<String>> list = new ArrayList<>();

        for(int i =0;i<200;i++){
            final  int b = i;

            CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{

                int randomInt =new Random().nextInt(2000);
                try {
                    Thread.sleep(randomInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(randomInt>=1000){
                    System.out.println(b+"等待的时间太长了："+randomInt);
                }

                return "返回值："+b+"等待的时间："+randomInt;

            },executor);

            CompletableFuture<String> asyncFuture  = CompletableFutureUtils.timeoutAfter(future,1,TimeUnit.SECONDS);

            list.add(asyncFuture);

        }

        long beginTime = System.currentTimeMillis();

        for(int i=0;i<list.size();i++){

            try {

                String retValue = list.get(i).get(1, TimeUnit.SECONDS);
                if (retValue!=null) {
                    System.out.println(retValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("遍历完用时："+(endTime-beginTime));

        threadBlock();
        System.out.println("sdfsdf");

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
