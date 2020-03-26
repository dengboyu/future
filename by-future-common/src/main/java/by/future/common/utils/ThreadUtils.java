package by.future.common.utils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池工具类
 *
 * @Author：by@Deng
 * @Date：2019/6/15 23:33
 */
public class ThreadUtils {

    private static ExecutorService executor = Executors.newFixedThreadPool(30);

    private ThreadUtils() {
    }

    public static ExecutorService getExecutorServiceInstance() {
        return executor;
    }


}
