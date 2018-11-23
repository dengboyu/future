package by.future.common.utils;


import by.future.entity.common.SignResultEntity;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 签名工具类
 *
 * @Author：by@Deng
 * @Date：2018/11/23 9:22
 */
public class SignUtils {


    /**
     * 签名算法,目前支持MD5算法
     * @param： timeout大于0时验证签名超时
     *
     * @Author：by@Deng
     * @Date：2018/6/1 10:10
     */
    public static SignResultEntity getSignCommon(Object object, String key, long timeout){

        SignResultEntity signResultEntity = new SignResultEntity();
        signResultEntity.setCode(-1);
        signResultEntity.setMessage("签名错误");

        String logTitle = "getSignCommon";
        HashMap<String,String> logMap = new HashMap<>();
        logMap.put("key",key);

        try {

            if(StringUtils.isEmpty(key)){
                signResultEntity.setMessage("key不能为空");
                return signResultEntity;
            }

            Field field = object.getClass().getDeclaredField("timestamp");
            field.setAccessible(true);
            Object val = field.get(object);

            if(val==null) return signResultEntity;

            if(timeout>0){
                //签名的时间戳timeout以内有效
                long timestamp = Long.valueOf(val.toString());
                if(System.currentTimeMillis()-timestamp > timeout){
                    signResultEntity.setCode(-2);
                    signResultEntity.setMessage("签名超时");
                    return signResultEntity;
                }
            }

            String signParam =  MapUtils.formatObjectFilter(object,false,true)+"&key="+key;
            System.out.println("打印参数："+signParam);

            String sign = Md5Crypt.apr1Crypt(signParam).toUpperCase();

            signResultEntity.setCode(1);
            signResultEntity.setSign(sign);

        } catch (Exception e) {
            //待写
        }

        return signResultEntity;
    }








}
