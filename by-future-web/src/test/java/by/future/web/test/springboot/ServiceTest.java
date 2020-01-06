package by.future.web.test.springboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author：by@Deng
 * @Date：2018/10/15 13:34
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    private static Logger logger = LoggerFactory.getLogger(ServiceTest.class);

    /**
     * 得到运行时系统cpu-内存相关信息
     *
     * @Author：by@Deng
     * @Date：2019/12/29 13:44
     */
    @Test
    public void printSystemInfo(){

        System.out.println(System.getProperties());

        System.out.println(System.getProperty("os.name"));

        System.out.println(Runtime.getRuntime().availableProcessors());

        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().freeMemory());


        //创建无用对象是非常耗时的
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        long sum = 0L;    //耗时703ms
        Long sum = 0L;      //耗时8000ms，有自动拆装箱，所以创建2^31个无用对象
        for (long i = 0; i <= Integer.MAX_VALUE; i++)
            sum += i;

        stopWatch.stop();

        System.out.println(sum);
        System.out.println("耗时："+stopWatch.getTotalTimeMillis());


    }

    @Test
    public void tesFor(){


        /*ThreadTestTwoEntity threadTestTwoEntity = new ThreadTestTwoEntity();
        threadTestTwoEntity.setStu("aaaa");
        threadTestTwoEntity.setAddress("中国");
        threadTestTwoEntity.setAge(11);*/

//        System.out.println(MapUtils.formatObject(threadTestTwoEntity,false));


        /*Map<String,String> map  = new HashMap<>();
        map.put("key1","ass");
        map.put("acd","23");
        map.put("zd","2311");
        map.put("io","90");

        84922、84923
        System.out.println(MapUtils.formatObjectMap(map,false));*/
        BigDecimal a = new BigDecimal(39993);
        BigDecimal b = new BigDecimal(400000);

        BigDecimal c = a.divide(b,2, BigDecimal.ROUND_HALF_UP);

        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);

        System.out.println("aaa:"+percent.format(c));

    }



    @Test
    public void testOOM(){
        //简单测试一下outOfMemory，向内存申请了4G的内存空间
        //因为内存没这么大，所以报内存溢出，1个int占用4个字节，B：字节
//        int[] arr = new int[1024 * 1024 *1024];

//        int[] arr = new int[1024 * 1024];

//        System.out.println(arr);

        ArrayList<String[]> list = new ArrayList();

        for(int i =0;i<1000;i++){
            String[] arr = new String[1024 * 1024];
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            list.add(arr);
        }

        System.out.println("完事了");

        Integer a = new Integer(400);
        Integer b = new Integer(500);
        Integer c = new Integer(600);

        AtomicReference<Integer> ar = new AtomicReference();
        ar.set(a);
        System.out.println(a.intValue());
        ar.compareAndSet(c, b);
        System.out.println(ar.get().intValue());

    }



    @Test
    public void testOther(){


    }


    @Test
    public void methodTwo(){


        ""




    }

    @Test
    public void methodThree(){

        try {
//            InputStream is =  new FileInputStream("/Users/by/Desktop/map.jpeg");
//
//            byte[] bytes = new byte[1024];
//            int a  =is.read(bytes,0,bytes.length);
//            System.out.println("sfd:"+bytes);

            int a = 127;
            int b = 128;
            int c = 256;

            Byte a1 = (byte)a;
            Byte b1 = (byte)b;
            Byte c1 = (byte)c;

            System.out.println(a1);
            System.out.println(b1);
            System.out.println(c1);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
