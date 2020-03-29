package by.future.web.test.springboot;


import by.future.common.utils.CommonUtils;
import by.future.common.utils.UUIDUtils;
import by.future.entity.test.PersonTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        collection.add(89);
        collection.add(64);
        collection.add(55);
        collection.add(112);
        collection.add(55);
        collection.add(55);
        collection.add(58);

        /*long count = collection.stream().filter(n->n==60).count();
        System.out.println(count);*/

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

//        Optional<Integer> optionalInteger = collection.stream().filter(n-> n>50).findFirst();

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
        integerList.add(5);
        integerList.add(4);
        integerList.add(5);
        integerList.add(3);

        List<Integer> newList = integerList.stream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println(newList);

//        System.out.println(integerList.stream().sorted((x,y)-> {if(x<4 && y<4) return -1; return 1;}).collect(Collectors.toList()));

//        System.out.println(integerList.stream().filter(n->n>4).mapToInt(Integer::intValue).sum());

        //多个比较，获取最大值
        //CouponItemDTO hotelCouponItemDTO = hotelCouponList.stream().max(Comparator.comparing(CouponItemDTO::getCouponPrice)).get();

        /*int max = integerList.stream().max(Integer::compareTo).get();
        System.out.println("Max的值:"+max);*/

//        int max = integerList.stream().mapToInt(r->r).max().getAsInt();
//        System.out.println("Max的值:"+max);

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


        List<PersonTest> personTestList = new ArrayList<>();

//        PersonTest personTest1 = new PersonTest();
//        personTest1.setPrice(new BigDecimal("11"));
//        personTestList.add(personTest1);
//
//        PersonTest personTest2 = new PersonTest();
//        personTest2.setPrice(new BigDecimal("12"));
//        personTestList.add(personTest2);
//
        PersonTest personTest3 = new PersonTest();
        personTest3.setId("1");
        personTest3.setPrice(new BigDecimal("14"));
        personTest3.setName("hello");
        personTestList.add(personTest3);

        PersonTest personTest4 = new PersonTest();
        personTest4.setId("1");
        personTest4.setPrice(new BigDecimal("10"));
        personTest4.setName("我1");
        personTestList.add(personTest4);

        PersonTest personTest5 = new PersonTest();
        personTest5.setId("2");
        personTest5.setPrice(new BigDecimal("10"));
        personTest5.setName("我1");
        personTestList.add(personTest5);

        //根据某个属性分组
        /*Map<BigDecimal,List<PersonTest>> mapList = personTestList.stream().collect(Collectors.groupingBy(PersonTest::getPrice));
        System.out.println(mapList);*/

        //reduce的拼接函数
//        System.out.println(personTestList.stream().map(n->n.getName()).reduce("begin",(x, y) -> x.concat(y)));

//        System.out.println(personTestList.stream().distinct().collect(Collectors.toList()));
//        System.out.println(personTestList.stream().filter(CommonUtils.distinctByKey(n->n.getName())).collect(Collectors.toList()));
//        System.out.println(personTestList);

//        List<Integer> testList = personTestList.stream().map(n->n.getPrice().intValue()).collect(Collectors.toList());


//        System.out.println(personTestList);
//
//        System.out.println("排序后"+personTestList.stream().sorted(Comparator.comparing(PersonTest::getPrice)).collect(Collectors.toList()));
//        System.out.println("排序后2"+personTestList.stream().sorted(Comparator.comparing(PersonTest::getPrice).reversed()).collect(Collectors.toList()));
//        System.out.println("排序后1"+personTestList.stream().max(Comparator.comparing(PersonTest::getPrice)).get());
//        System.out.println("排序后2"+personTestList.stream().min(Comparator.comparing(PersonTest::getPrice)).get());


    }


    @Test
    public void testConfigAnnotation() {

        List<ParallelEntity> parallelEntityList = new ArrayList<>();

        ParallelEntity parallelEntity = new ParallelEntity("中国");
        parallelEntityList.add(parallelEntity);

        parallelEntity = new ParallelEntity("英国");
        parallelEntityList.add(parallelEntity);

        parallelEntity = new ParallelEntity("韩国");
        parallelEntityList.add(parallelEntity);

        parallelEntity = new ParallelEntity("东南亚");
        parallelEntityList.add(parallelEntity);

        parallelEntity = new ParallelEntity("欧洲");
        parallelEntityList.add(parallelEntity);

        parallelEntity = new ParallelEntity("西班牙");
        parallelEntityList.add(parallelEntity);

        long beginTime = System.currentTimeMillis();
        List<String> nameList = parallelEntityList.stream().map(parallelEntityEach->parallelEntityEach.getName()).collect(Collectors.toList());
        long endTime = System.currentTimeMillis();

        System.out.println("串行用时:"+(endTime - beginTime)+"输出的信息:"+nameList);


        long parallelBeginTime = System.currentTimeMillis();
        List<String> parallelNameList = parallelEntityList.parallelStream().map(parallelEntityEach->parallelEntityEach.getName()).collect(Collectors.toList());
        long parallelEndTime = System.currentTimeMillis();

        System.out.println("并行用时:"+(parallelEndTime - parallelBeginTime)+"输出的信息:"+parallelNameList);


    }

    private class ParallelEntity{

        private String name;

        public ParallelEntity(String name) {
            this.name = name;
        }

        public String getName(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return name;
        }
    }


    @Test
    public void testCommon(){

        for(int i=0;i<50;i++){
            String a = UUIDUtils.getUUID();
            System.out.println(i+":"+CommonUtils.shardCore(4,a));
            System.out.println(i+":"+CommonUtils.shardCore(8,a));
        }


    }









}
