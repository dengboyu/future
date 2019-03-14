package by.future.web.test.springboot;


import by.future.common.cache.SafeBuffer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author：by@Deng
 * @Date：2018/10/15 13:34
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {


    @Test
    public void tesFor(){

//        ThreadTestTwoEntity threadTestTwoEntity = new ThreadTestTwoEntity();
//        threadTestTwoEntity.setStu("aaaa");
//        threadTestTwoEntity.setAddress("中国");
//        threadTestTwoEntity.setAge(11);

//        System.out.println(MapUtils.formatObject(threadTestTwoEntity,false));


        /*Map<String,String> map  = new HashMap<>();
        map.put("key1","ass");
        map.put("acd","23");
        map.put("zd","2311");
        map.put("io","90");

        System.out.println(MapUtils.formatObjectMap(map,false));*/

        /*PersonExtends personExtends = new PersonExtends();


        personExtends.setAge(11);
        personExtends.setAddress("地址");

        System.out.println(null instanceof PersonTest);
        System.out.println(personExtends instanceof PersonTest);
        PersonTest personTest = personExtends;

        System.out.println(personTest);
        System.out.println(personExtends);*/


    }

    @Test
    public void test(){

        SafeBuffer<String> safeBuffer = new SafeBuffer();

        for(int i=0;i<10;i++){
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1000);

                        safeBuffer.put("hi,我是生产者");

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

                        Thread.sleep(1000);

                        String data = safeBuffer.get();

                        System.out.println("我获取到的数据是："+data);

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






}