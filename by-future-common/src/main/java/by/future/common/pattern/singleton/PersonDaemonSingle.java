package by.future.common.pattern.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 单例加载启动后台线程
 *
 * @author by@Deng
 * @create 2019-08-21 23:24
 */
public class PersonDaemonSingle {

    private static volatile PersonDaemonSingle personDamonSingle = null;
    private Thread daemonThread;

    private PersonDaemonSingle() {

        daemonThread = new Thread(()->{

            System.out.println("我是PersonDaemon初始化启动后台线程");
            System.out.println("当前线程："+Thread.currentThread().getName());

            while (!Thread.currentThread().isInterrupted()){
                System.out.println("我是后台线程,不停的做事");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setName("thread-person-daemon-single");
        daemonThread.setDaemon(true);

        daemonThread.start();
    }


    /**
     * 获取单实例
     *
     * @Author: by@Deng
     * @Date: 2019-08-21 23:28
     */
    public static PersonDaemonSingle getInstance() {

        if (personDamonSingle == null) {
            synchronized (PersonDaemonSingle.class) {
                if (personDamonSingle == null) {
                    personDamonSingle = new PersonDaemonSingle();
                }
            }
        }
        return personDamonSingle;
    }


}
