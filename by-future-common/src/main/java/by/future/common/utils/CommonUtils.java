package by.future.common.utils;


import org.apache.commons.lang3.StringUtils;

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


    /**
     * shard平均分配某个字段
     *
     * @Author: by@Deng
     * @Date: 2019/9/11 7:40 下午
     */
    public static int shardCore(int shardCount, String shardField) {
        long code = 0;
        if (StringUtils.isNotEmpty(shardField)) {
            for (int i = 0; i < shardField.length(); i += 2) {
                code *= 16777619;
                code &= 0xffffffffL; // Convert to uint
                code ^= shardField.charAt(i);
            }
        }

        int shardId = (int) (code & (shardCount-1)) + 1;

        return shardId;
    }


}
