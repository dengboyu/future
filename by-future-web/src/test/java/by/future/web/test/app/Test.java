package by.future.web.test.app;

import by.future.web.test.DeadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by@Deng
 * @create 2020-04-11 10:48
 */
public class Test {



    public static void main(String[] args) throws Exception {

        DeadLock dieLock = new DeadLock();
//        dieLock.testA();


        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0;i<10;i++){
            executorService.submit(dieLock);
        }

        executorService.shutdown();

    }

}
