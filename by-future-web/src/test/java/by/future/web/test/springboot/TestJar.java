package by.future.web.test.springboot;

import by.future.common.utils.ThreadUtils;
import by.future.web.test.springboot.jstorm.CallAbleTest;

import java.util.concurrent.*;

/**
 * @author by@Deng
 * @create 2019-08-24 14:19
 */
public class TestJar {

    public static void main(String[] args) {
//        ExecutorService executor = ThreadUtils.getExecutorServiceInstance();

        for(int i =0;i<10;i++){

            test(i);
        }

    }

    private static void test(int i){

        ExecutorService executor =  ThreadUtils.getExecutorServiceInstance();
        CallAbleTest callAbleTest1 = new CallAbleTest(i);
        CallAbleTest callAbleTest2 = new CallAbleTest(i);
        CallAbleTest callAbleTest3 = new CallAbleTest(i);

        Future f1 = executor.submit(callAbleTest1);
        Future f2 = executor.submit(callAbleTest2);
        Future f3 = executor.submit(callAbleTest3);

        try {

            System.out.println("线程池活动个数："+((ThreadPoolExecutor)executor).getActiveCount());

            Integer s1 = (Integer) f1.get();

            System.out.println("线程池活动个数："+((ThreadPoolExecutor)executor).getActiveCount());

            Integer s2 = (Integer) f2.get();

            System.out.println("线程池活动个数："+((ThreadPoolExecutor)executor).getActiveCount());

            Integer s3 = (Integer) f3.get();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(((ThreadPoolExecutor)executor).getPoolSize());

    }
}
