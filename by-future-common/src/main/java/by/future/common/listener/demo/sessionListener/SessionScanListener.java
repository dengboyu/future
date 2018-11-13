package by.future.common.listener.demo.sessionListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

/**
 * session扫描器
 *
 * @author by@Deng
 * @create 2017-10-13 21:32
 */
public class SessionScanListener implements HttpSessionListener, ServletContextListener {

    //线程安全集合
    private List<HttpSession> sessionList = Collections.synchronizedList(new LinkedList<>());
    private Object lock=new Object();    //锁

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        HttpSession session = httpSessionEvent.getSession();
        synchronized (lock){
            sessionList.add(session);
        }

        System.out.println("session被创建了");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session被销毁了");
    }


    //servlet的方法重写
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Timer timer = new Timer();
        timer.schedule(new MyTask(sessionList,lock),0,1000*60*5);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

/**
 * 指定任务
 * @author by@Deng
 * @date 2017/10/13 下午9:43
 */
class MyTask extends TimerTask{

    private List<HttpSession> list;
    private Object lock;

    public MyTask(List<HttpSession> list, Object lock) {
        this.list = list;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized(lock){
            if(list.size()>0){
                for(int i=list.size()-1;i>=0;i--){
                    HttpSession session = list.get(i);
                    if((System.currentTimeMillis() - session.getLastAccessedTime()) > 1000 * 60 ){
                        session.invalidate();//摧毁session
                        list.remove(session);
                    }
                }
            }
        }
    }
}