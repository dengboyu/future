package by.future.common.utils;

import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author by@Deng
 * @create 2018-06-17 12:16
 */
public class ReflectUtils {


    /**
     * 根据某个对象的名称和方法去执行不带参数方法
     *
     * @Author: by@Deng
     * @Date: 2018/6/17 下午12:17
     */
    public static Object execute(String className, String methodName) {

        try {

            Class clazz = Class.forName(className);
            Method m1 = clazz.getDeclaredMethod(methodName);
            return m1.invoke(clazz.newInstance());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    /**
     * 根据某个对象的名称和方法去执行带参数方法
     *
     * @Author: by@Deng
     * @Date: 2018/6/17 下午12:17
     */
    public static Object execute(String className, String methodName, Object parameter) {

        try {

            if(parameter==null) return null;

            Class clazz = Class.forName(className);
            Method m1 = clazz.getDeclaredMethod(methodName,parameter.getClass());
            return m1.invoke(clazz.newInstance(),parameter);

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
