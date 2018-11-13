package by.future.common.listener.demo.sessionListener;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登陆监听器
 *
 * @author by@Deng
 * @create 2017-10-13 23:40
 */
public class LoginListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        Object object = httpSessionBindingEvent.getValue(); //得到添加的值
        HttpSession session = httpSessionBindingEvent.getSession();

        //从servletContext域中得到保存所有用户
        ServletContext servletContext = httpSessionBindingEvent.getSession().getServletContext();
        Map map = (Map) servletContext.getAttribute("loginMap");
        if(map==null){
            map = new HashMap();
            servletContext.setAttribute("loginMap",map);
        }

        //如果是用户登陆了
//        if(object instanceof SysUserEntity){
//            map.put(session.getId(),session);
//        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
