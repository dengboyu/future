package by.future.common.utils;


import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具类
 *
 * @Author：by@Deng
 * @Date：2018/12/10 10:30
 */
public class RandomUtils {

    private static final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();


    /**
     * 随机生成length长度
     *
     * @Author：by@Deng
     * @Date：2018/12/10 10:31
     */
    public static long generateRandomNum(int length){
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<length;i++){
            sb.append(threadLocalRandom.nextInt(9));
        }
        return Long.valueOf(sb.toString());
    }





}
