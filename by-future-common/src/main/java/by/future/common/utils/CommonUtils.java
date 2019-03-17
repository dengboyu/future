package by.future.common.utils;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 公共工具类
 *
 * @Author：by@Deng
 * @Date：2019/3/15 18:46
 */
public class CommonUtils {


    /**
     * stream的distinct的根据属性去重
     *
     * @Author：by@Deng
     * @Date：2019/3/15 18:47
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }




}
