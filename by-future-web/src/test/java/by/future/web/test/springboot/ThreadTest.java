package by.future.web.test.springboot;


import by.future.common.cache.SafeBuffer;
import by.future.thread.demo.impl.ThreadDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        new Thread(new ThreadDemo()).start();

    }





}
