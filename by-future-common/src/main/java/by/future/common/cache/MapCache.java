package by.future.common.cache;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局缓存map
 *
 * @Author：by@Deng
 * @Date：2019/1/4 9:55
 */
public class MapCache {

    private static Map<Object,Object> cacheMap = new ConcurrentHashMap<>();


    /**
     * 获取全局cacheMap
     *
     * @Author：by@Deng
     * @Date：2019/1/4 9:56
     */
    public static Map<Object,Object> getCacheMap(){
        return cacheMap;
    }


    /**
     * 设置key/value
     *
     * @Author：by@Deng
     * @Date：2019/1/4 10:21
     */
    public static void setCacheMap(Object key,Object value){
        if(cacheMap==null) cacheMap = new ConcurrentHashMap<>();
        cacheMap.put(key,value);
    }


    /**
     * 获取key的value
     *
     * @Author：by@Deng
     * @Date：2019/1/4 10:25
     */
    public static Object get(Object key){
        if(cacheMap==null) return null;
        return cacheMap.get(key);
    }


    /**
     * 获取key的string
     *
     * @Author：by@Deng
     * @Date：2019/1/4 10:26
     */
    public static String getString(Object key) {
        if(cacheMap==null) return null;
        return (String) cacheMap.get(key);
    }


    /**
     * 删除key
     *
     * @Author：by@Deng
     * @Date：2019/1/4 10:28
     */
    public static void remove(Object key) {
        if(cacheMap!=null) cacheMap.remove(key);
    }


    /**
     * 销毁cacheMap
     *
     * @Author：by@Deng
     * @Date：2019/1/4 9:58
     */
    public static void destoryCacheMap(){
        cacheMap = null;
    }




}
