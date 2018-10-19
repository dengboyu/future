package by.future.common.listener.demo.sessionListener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计在线人数监听器
 *
 * @author by@Deng
 * @create 2017-10-13 19:21
 */
public class OnLineCountListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineNums = (Integer) servletContext.getAttribute("onlineNums");
        if(onlineNums==null){
            servletContext.setAttribute("onlineNums",1);
        }else{
            onlineNums++;
            servletContext.setAttribute("onlineNums",onlineNums);
        }


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineNums = (Integer) servletContext.getAttribute("onlineNums");
        servletContext.setAttribute("onlineNums",onlineNums--);

    }
}
