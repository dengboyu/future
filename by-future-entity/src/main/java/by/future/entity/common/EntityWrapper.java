package by.future.entity.common;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：by@Deng
 * @Date：2019/1/24 15:30
 */
public class EntityWrapper<T> {

    private Map<String, T> entityWrapper = new ConcurrentHashMap<>();

    public Map<String, T> getEntityWrapper() {
        return entityWrapper;
    }

    /**
     * 添加key
     *
     * @Author：by@Deng
     * @Date：2019/1/24 17:03
     */
    public void addEntry(String key,T value){

        entityWrapper.put(key,value);

    }

}
