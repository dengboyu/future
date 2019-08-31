package by.future.web.test.springboot;


import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.FileSystem;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author：by@Deng
 * @Date：2018/10/15 13:34
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

    Logger logger = LoggerFactory.getLogger(ServiceTest.class);

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

        ArrayList<int[]> list = new ArrayList();

        for(int i =0;i<1000;i++){
            int[] arr = new int[1024 * 1024];
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

//        System.load("//Users//by//Desktop//file");
//        System.out.println(System.getProperty("name"));
//        System.out.println();
//        System.out.println(System.getProperty("file.base"));

//        System.load("");

//        File file = new File()

        Map<String,String> map = new HashMap<>();
        map.put("aa","aaa");
        map.put("bb","bbb");

        Map<String,String> map1 = new HashMap<>();
        map1.put("aa","a");
        map1.put("cc","ccc");

        map1.putAll(map);
        System.out.println(map1);
    }


}
