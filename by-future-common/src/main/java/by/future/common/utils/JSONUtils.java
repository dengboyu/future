package by.future.common.utils;


import by.future.entity.common.EntityWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * json工具类
 *
 * @Author：by@Deng
 * @Date：2019/1/24 15:22
 */
public class JSONUtils {


    /**
     * 反序列化复杂json
     *
     * @Author：by@Deng
     * @Date：2019/1/24 16:28
     */
    public static <T> EntityWrapper<T> getEntityWrapper(String jsonString, Class<T> prototype) {
        ObjectMapper objectMapper = new ObjectMapper();

        EntityWrapper<T> wrapper = new EntityWrapper();

        try {
            JsonNode root = objectMapper.readTree(jsonString);
            Iterator<Map.Entry<String, JsonNode>> elements = root.fields();

            while (elements.hasNext()) {

                Map.Entry<String, JsonNode> node = elements.next();
                String key = node.getKey();
                T element = objectMapper.readValue(node.getValue().toString(), prototype);

                wrapper.addEntry(key, element);
            }

            return wrapper;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}