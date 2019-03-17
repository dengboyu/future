package by.future.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author：by@Deng
 * @Date：2019/3/17 11:30
 */
public class Stringutils extends StringUtils {


    public static long generateUUID(String message) {

        if(isEmpty(message)) return 0;

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //AdContentId + schemaId 做MD5
        byte[] bytes = md5.digest(message.getBytes());
        StringBuffer sb = new StringBuffer();

        //缩短字符串长度：对每一位取后4位，做16进制处理
        for (byte b : bytes) {
            int val = (int) b & 0xf;
            sb.append(Integer.toHexString(val));
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
