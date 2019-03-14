package by.future.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 实现了ApplicationContextAware接口的类，在spring容器启动后将本身作为参数，执行setApplicationContext()方法
 * 从而可以获取spring容器中的任意bean
 *
 * @author by@Deng
 * @create 2018-09-08 22:03
 */
@Component
public class GetBeanFromApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 从静态变量ApplicationContext中获得不带参数Bean
     *
     * @author by@Deng
     * @date 2017/12/6 下午10:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中获得带参数Bean
     *
     * @author by@Deng
     * @date 2017/12/6 下午10:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name,Object... object) {
        return (T) applicationContext.getBean(name,object);
    }

    /**
     * 从静态变量ApplicationContext中获得Bean
     *
     * @author by@Deng
     * @date 2017/12/6 下午10:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name,Class<T> clazz) {
        return (T) applicationContext.getBean(name,clazz);
    }


    /**
     * 从静态变量ApplicationContext中获得Bean
     * @author by@Deng
     * @date 2017/12/6 下午10:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * 从静态变量ApplicationContext中获得带参数Bean
     * @author by@Deng
     * @date 2017/12/6 下午10:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz,Object... object) {
        return (T) applicationContext.getBean(clazz,object);
    }

}