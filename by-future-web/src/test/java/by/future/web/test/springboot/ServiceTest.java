package by.future.web.test.springboot;


import by.future.common.exception.ByException;
import by.future.common.utils.TimeUtils;
import by.future.servicebiz.flink.wordcount.FlinkInstance;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

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
        int[] arr = new int[1024 * 1024 *1024];

        System.out.println(arr);

    }


}
