package by.future.common.listener.demo.servletListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听servlet的创建和销毁案例
 *
 * @author by@Deng
 * @create 2017-10-13 08:28
 */
public class ServletListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext被创建了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext被销毁了");
    }
}
