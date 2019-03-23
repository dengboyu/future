package by.future.common.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 短链接生成方法
 *
 * @author by@Deng
 * @create 2018-07-21 11:42
 */
public class ShortCodeUtils {


    public static final String CODE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //码表
    public static final String[] codeArr = {"Q","G","e","F","7","k","b","4","K","o","u","w","S","D","W","m","5","c","A","9","1","a","T","I","3","P","j","X","J","2","s","R","y","x","n","p","f","N","h","l","z","q","r","v","M","i","C","8","Y","t","V","0","O","Z","d","g","H","B","6","L","U","E"};

    private static final List<String> strList = new ArrayList<>(Arrays.asList(codeArr));

    /*//将code随机打乱
    public static void main(String[] args) {
        List<String> list = Arrays.asList(CODE.split(""));
        Collections.shuffle(list);
        System.out.println(list);
    }*/



    /**
     * 短链接转换
     *
     * @Author: by@Deng
     * @Date: 2018/7/21 上午11:44
     */
    public static String toBase62(long num){
        if(num<1) return null;

        String code="";

        while (num > 62) {
            code = codeArr[(int)(num % 62)] + code;
            num = num/62;
        }

        if (num>0) {
            code = codeArr[(int)num] +code;
        }

        return code;
    }


    /**
     * 短连接转换
     *
     * @Author: by@Deng
     * @Date: 2018/7/21 上午11:44
     */
    public static String toRandomBase62(long num){

        if(num<1) return null;
        return toBase62(insertRandomBitPer5Bits(num));

    }

    /**
     * 根据base62解析成long
     *
     * @Author: by@Deng
     * @Date: 2018/8/28 下午10:16
     */
    public static Long strToLong(String baseStr){

        if (StringUtils.isEmpty(baseStr)) return null;

        long result = 0;
        String[] strArr = baseStr.split("");
        for (int i=1;i<=strArr.length;i++){

            int charIndex =strList.indexOf(strArr[i-1]);
            if(charIndex<0) return null;

            result += charIndex * ((long)Math.pow(62,strArr.length-i));

        }

        return result;
    }

    /**
     * 每隔5位随机插入数值
     *
     * @Author：by@Deng
     * @Date：2018/8/28 19:36
     */
    private static long insertRandomBitPer5Bits(long num){
        long result = num;
        long high = num;

        //todo 有待商榷
        for(int i=0;i<10;i++){

            if(high==0){
                break;
            }

            int pos = 5 * (i +1 )  + i;
            high = result >> pos;

            result = ((high <<1 | RandomUtils.nextInt(0,2)) << pos) | (result & (-1L >>> 64-pos)) ;
        }

        return result;

    }


}
