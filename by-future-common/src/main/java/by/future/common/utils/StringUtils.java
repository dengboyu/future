package by.future.common.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author：by@Deng
 * @Date：2019/3/17 11:30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * 对文案生成long类型
     * 
     * @Author：by@Deng
     * @Date：2019/3/17 11:45
     */
    public static long generateMatch(String message) {

        if(isEmpty(message)) return 0;

        StringBuffer sb = new StringBuffer();

        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            //AdContentId + schemaId 做MD5
            byte[] bytes = md5.digest(message.getBytes());

            //缩短字符串长度：对每一位取后4位，做16进制处理
            for (byte b : bytes) {
                int val = (int) b & 0xf;
                sb.append(Integer.toHexString(val));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //Hash String to Long
        return SDBMHash(sb.toString().toCharArray());
    }

    private static long SDBMHash(char[] str) {
        long hash = 0L;
        for (char c : str)
            hash = (c) + (hash << 6) + (hash << 16) - hash;

        return (hash & Long.MAX_VALUE);
    }


}
