package by.future.web.websocket.tomcat8.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * 实现该ServerApplicationConfig接口,会在项目启动时自动执行
 * @author by@Deng
 * @create 2017-12-16 09:44
 */
public class SocketDemo implements ServerApplicationConfig {

    private static final Logger logger = LoggerFactory.getLogger(SocketDemo.class);

    /**
     * 注解方式
     * @author by@Deng
     * @date 2017/12/16 上午9:45
     */
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> set) {

        System.out.println("websocket启动了,个数:--->"+set.size());
        for(Class clazz:set){
            System.out.println("扫描到的类名是:"+clazz.toString());
        }

        return set; //返回该set(扫描到的包,起到过滤作用)
    }

    /**
     * 接口方式编程
     * @author by@Deng
     * @date 2017/12/16 上午9:44
     */
    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> set) {
        return null;
    }


}
