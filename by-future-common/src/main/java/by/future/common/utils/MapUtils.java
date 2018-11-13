package by.future.common.utils;


import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * map工具类
 *
 * @Author：by@Deng
 * @Date：2018/5/4 17:57
 */
public class MapUtils {

    //忽略签名字段
    private static final String[] ignoreKeyArr = {"schema","serialversionuid","sign"};


    /**
     * 根据key按字典排序
     *
     * @Author：by@Deng
     * @Date：2018/5/4 17:58
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByKey(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list,(o1,o2)-> o1.getKey().toString().compareTo(o2.getKey().toString()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    /**
     * 根据value按字典排序
     *
     * @Author：by@Deng
     * @Date：2018/5/4 18:14
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1,o2)-> o1.getKey().toString().compareTo(o2.getKey().toString()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    /**
     * 获取Map对象忽略大小写
     * @param map
     * @param key
     * @return
     */
    public static<T> T getMapValueIgnoreCase(Map<String, T> map,String key){
        for(String k:map.keySet()){
            if(k.equalsIgnoreCase(key)){
                return map.get(k);
            }
        }
        return null;
    }


    /**
     * 判断是否存在Key，忽略大小写
     * @param map
     * @param key
     * @param <T>
     * @return
     */
    public static<T> Boolean mapContainsKey(Map<String, T> map,String key){
        for(String k:map.keySet()){
            if(k.equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }


    /**
     * map对象键值转换成小写的
     * @param map
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> mapKeyToLowerCase(Map<String, T> map, Boolean isUpperCase) {
        Map<String, T> mapResult = new HashedMap();
        for (String k : map.keySet()) {
            if(isUpperCase)
                k=k.toUpperCase();
            else
                k=k.toLowerCase();
            mapResult.put(k, map.get(k));
        }
        return mapResult;
    }


    /**
     * List<map>对象键值转换成小写的
     * @param lst
     * @param <T>
     * @return
     */
    public static<T> List<Map<String, T>> listMapKeyToLowerCaseComm(List<Map<String,T>> lst,Boolean isUpperCase) {
        List<Map<String, T>> lstMapLower = new ArrayList<>();
        if (lst != null && lst.size() > 0) {
            for (Map<String, T> m : lst) {
                Map<String, T> mapLower = mapKeyToLowerCase(m, isUpperCase);
                lstMapLower.add(mapLower);
            }
        }
        return lstMapLower;
    }


    /**
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * @param paraMap   要排序的Map对象,非空
     * @param urlEncode   是否需要URLENCODE
     * @return
     */
    public static<T> String formatObjectMap(Map<String, T> paraMap, boolean urlEncode) {
        String buff = "";
        Map<String,T> tmpMap = paraMap;
        try {
            List<Map.Entry<String, T>> infoIds = new ArrayList<>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds,(o1,o2) -> o1.getKey().compareTo(o2.getKey()));

            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, T> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    Object val = item.getValue();
                    if(urlEncode && val instanceof String) {
                        val = URLEncoder.encode(val.toString(),"utf-8");
                    }
                    buf.append(key + "=" + val).append("&");
                }
            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        }catch (Exception e) {
//            throw new SysException(e.getMessage());
        }
        return buff;
    }



    /**
     * 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * @param urlEncode   是否需要URLENCODE
     * @return
     */
    public static String formatObject(Object object, boolean urlEncode) {
        String buff = "";
        Field[] fields = object.getClass().getDeclaredFields();

        try {
            List<Field> infoIds = Arrays.asList(fields);

            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, (o1,o2)-> o1.getName().compareTo(o2.getName()));

            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Field item : infoIds) {
                if (StringUtils.isNotBlank(item.getName())) {
                    item.setAccessible(true);

                    String key = item.getName();
                    Object val = item.get(object);
                    if(!Arrays.asList(ignoreKeyArr).contains(key.toLowerCase()) && val !=null ) {
                        if(urlEncode && val instanceof String){
                            val = URLEncoder.encode(val.toString(),"utf-8");
                        }
                        buf.append(key + "=" + val).append("&");
                    }
                }
            }
            buff = buf.toString();
            if (StringUtils.isNotEmpty(buff)) {
                buff = buff.substring(0, buff.length() - 1);
            }
        }catch (Exception e) {
//            throw new ZealException(e.getMessage());
        }
        return buff;
    }


}
