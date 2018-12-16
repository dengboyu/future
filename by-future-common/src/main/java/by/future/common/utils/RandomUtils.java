package by.future.common.utils;


/**
 * 随机数工具类
 *
 * @Author：by@Deng
 * @Date：2018/12/10 10:30
 */
public class RandomUtils {


    /**
     * 随机生成length长度
     *
     * @Author：by@Deng
     * @Date：2018/12/10 10:31
     */
    public static long generateRandomNum(int length){
        return (long)((Math.random()*9+1)*Math.pow(10,length));
    }





}
