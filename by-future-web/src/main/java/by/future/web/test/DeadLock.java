package by.future.web.test;

import java.util.Random;

/**
 * @author by@Deng
 * @create 2020-04-12 13:46
 */
public class DeadLock implements Runnable{

    private Object A = new Object();
    private Object B = new Object();

    @Override
    public void run() {
        try {
            int a = new Random().nextInt(10);
            System.out.println("a的值:"+a);
            if(a % 2==0){
                testB();
            }else {
                testA();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testA() throws Exception{

        synchronized (A){

            Thread.sleep(2000);

            System.out.println("输出A");

            synchronized (B){
                Thread.sleep(2000);
                System.out.println("获取B锁");
            }
        }
    }


    public void testB() throws Exception{

        synchronized (B){

            Thread.sleep(2000);

            System.out.println("输出B");

            synchronized (A){
                System.out.println("获取A锁");
            }
        }


    }

}
