package by.future.web.test.springboot;


import by.future.common.utils.JSONUtils;
import by.future.entity.test.PersonTest;
import by.future.entity.common.EntityWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：by@Deng
 * @Date：2018/10/18 14:58
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTest {

    @Value("${name}")
    private String name;

    @Test
    public void contextLoads() {

        List<Integer> collection = new ArrayList<>();
        collection.add(14);
        collection.add(43);
        collection.add(64);
        collection.add(89);
        collection.add(5);
        collection.add(112);
        collection.add(55);
        collection.add(55);
        collection.add(58);

        /*int[] a ={0};
        collection.stream().forEach(n->{
            a[0] = a[0]+1;
            System.out.println(a[0]);
        });*/

        //collection.stream() 与 collection.parallelStream()区别：串行与并行操作，并行影响顺序

        //foreach遍历操作
//        collection.stream().forEach(n ->{
//            System.out.println(n);
//            if(n>500){
//                System.out.println(n);
//            }else{
//                return;
//            }
//        });
//        System.out.println("fsda");


        //map遍历操作
//        List<Integer> mapList =collection.stream().map(n-> {
//            if(n<50){
//                System.out.println(n);
//                return n;
//            }
//            return null;
//        }).collect(Collectors.toList());

        //filter过滤操作
//        List<Integer> filterList = collection.stream().filter(n-> n > 500).collect(Collectors.toList());

//        Optional<Integer> optionalInteger = collection.stream().filter(n->{
//            return n>50;
//        }).findFirst();

//        System.out.println(filterList);
//        System.out.println(optionalInteger.isPresent());
//        System.out.println(optionalInteger.get());

/*
        //limit获取前n个元素
        List<Integer> limitList =collection.stream().limit(3).collect(Collectors.toList());
        System.out.println(limitList);


        //skip扔掉前n个元素
        List<Integer> skipList = collection.stream().skip(3).collect(Collectors.toList());
        //System.out.println(skipList);


        //limit/skip可以链式编程
        List<Integer> limitSkipList = collection.stream().skip(2).limit(3).collect(Collectors.toList());
        //System.out.println(limitSkipList);


        //sort排序操作
        List<Integer> sortList = collection.stream().sorted((a,b)->{
            return a-b;
        }).collect(Collectors.toList());
        System.out.println(sortList);


        //max、min获取最大、小值
        //mapToInt、mapToLong映射


        //distinct去除重复元素
        List<Integer> distinctList = collection.stream().distinct().collect(Collectors.toList());
//        System.out.println(distinctList);
//        System.out.println(collection);

        //match匹配方法
        //allMatch(所有元素是否都匹配)、anyMatch(有一个元素匹配就可以)、noneMatch(所有元素都不匹配)
        boolean allMatchCon = collection.stream().allMatch((n)->{
             return n>50;
        });
        boolean anyMatchCon = collection.stream().anyMatch((n)->{
           return n>50;
        });
        boolean noneMatchCon = collection.stream().noneMatch((n)->{
           return n>50;
        });
//        System.out.println(allMatchCon +"---"+anyMatchCon+"---"+noneMatchCon);

*/


        //以上可以链式操作


    }


    @Test
    public void CollectionTest() throws Exception {

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(4);
        integerList.add(5);
        integerList.add(3);
        integerList.add(5);

//        integerList.stream().forEach(n->{
//            if(n<4) return;
//            System.out.println(n);
//        });

//        Optional<Integer> optionalInteger =  integerList.stream().filter(n->n==5).findFirst();

//        System.out.println(optionalInteger.isPresent());
//        System.out.println(optionalInteger.get());

//        Calendar calendar = Calendar.getInstance();
//        int month = calendar.get(Calendar.MONTH)+1;
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        System.out.println(month+"==="+day);

/*

        Collections.sort(integerList,(Integer o1, Integer o2)->o2.compareTo(o1));

        System.out.println(integerList);
*/


//        String s = "a";
//        final int a;
//
//        if(StringUtils.equals(s,"aa")){
//            a=2;
//        }else{
//            a = 4;
//        }
//
//        Optional<Integer> optionalInteger =  integerList.stream().filter(n->n==a).findFirst();
//
//        System.out.println(optionalInteger.isPresent());
//        System.out.println(optionalInteger.get());
    }


    @Test
    public void testConfigAnnotation() {

        String test = "{\"dm_vincent\":{\"id\":1,\"name\":\"dm_vincent\",\"age\":28},\"dm_vincent2\":{\"id\":2,\"name\":\"dm_vincent2\",\"age\":29},\"dm_vincent3\":{\"id\":3,\"name\":\"dm_vincent3\",\"age\":30}}";

        EntityWrapper<PersonTest> entityWrapper = JSONUtils.getEntityWrapper(test, PersonTest.class);
        System.out.println(entityWrapper);
        System.out.println(entityWrapper.getEntityWrapper().get("dm_vincent"));
    }


}
