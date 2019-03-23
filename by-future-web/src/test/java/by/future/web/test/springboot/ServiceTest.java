package by.future.web.test.springboot;


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

        System.out.println(MapUtils.formatObjectMap(map,false));*/


    }



    @Test
    public void testOOM(){
        //简单测试一下outOfMemory，向内存申请了4G的内存空间
        //因为内存没这么大，所以报内存溢出，1个int占用4个字节，B：字节
        int[] arr = new int[1024 * 1024 *1024];

        System.out.println(arr);

    }

}
