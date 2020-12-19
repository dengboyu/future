package by.future.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author by@Deng
 * @create 2020-04-04 21:23
 */
@ComponentScan("by.future")
@Configuration
public class StartTest {

    private static Logger logger = LoggerFactory.getLogger(StartTest.class);
//    private static Logger logger = LogManager.getLogger(StartTest.class);

    public static void main(String[] args) {


        int discountA = 10;


    }


    public void discountPrice() {

        int a = 10;
        int ab = 30;
        int bc = 40;

        int lastAmount = 2 * a + ab * 3 + bc * 3;
        System.out.println(lastAmount);
    }

    //mapping关系
    private static HashMap<String, Integer> map = new HashMap<String,Integer>(){{
        put("^", 1);
        put("%", 5);
        put("&", 10);
        put("*", 50);
        put("$", 100);
        put("#", 500);
        put("@", 1000);
        put("-", 5000);
    }};


    public void transfer() {

        //瓦星日历
        String wkDate = "@- $$$ &$ %^^^年 &^^月 ^&日 %^^时 &*%^^^分 *%^秒";
        String earthDate = "4398年12月9日 07时48分56秒";

        //年：千位：相减，空格，百位、十位、各位：相加
        int year = computeYear(wkDate);

        //月：相加
        int month = 0;

        //日：相减
        int day = 0;

        //时：相加
        int hour = 0;

        //分：相加
        int min = 0;

        //秒：相加
        int sec = 0;

    }


    //计算年
    private int computeYear(String date){

        int index = date.indexOf("年");
        String yearDate = date.substring(0,index);

        String[] yearArr = yearDate.split("\\s+");

        //todo 抽出方法，通过遍历获取

        //千位值
        int qianwei = map.get(yearArr[0].charAt(1)) - map.get(yearArr[0].charAt(0));

        //百位值
        int baiwei = map.get(yearArr[1].charAt(0)) + map.get(yearArr[1].charAt(1)) + map.get(yearArr[1].charAt(2));

        //十位
        int shiwei = map.get(yearArr[2].charAt(0)) + map.get(yearArr[2].charAt(1));

        //个位
        int gewei = map.get(yearArr[3].charAt(0)) + map.get(yearArr[3].charAt(1)) + map.get(yearArr[3].charAt(2)) + map.get(yearArr[3].charAt(3));

        return qianwei + baiwei + shiwei +gewei ;
    }

    //计算月
    public int computeMonth(String date){

        int beginIndex = date.indexOf("年");
        int index = date.indexOf("月");
        String monthDate = date.substring(beginIndex,index).trim();

        int month = 0;
        for(int i =0;i<monthDate.length();i++){

            month += map.get(monthDate.charAt(i));
        }

        return month;
    }

}
