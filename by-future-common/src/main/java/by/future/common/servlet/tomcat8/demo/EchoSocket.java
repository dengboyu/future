package by.future.common.servlet.tomcat8.demo;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;

/**
 * Endpoint相当于servlet,相当于websocket的服务端程序
 *
 * @author by@Deng
 * @create 2017-10-27 20:43
 */
@ServerEndpoint("/echoWebSocket")
public class EchoSocket  {

    private String username;    //当前登录账户
    private Set<String> stringSet = new HashSet<>();    //所有用户

    /**
     * 验证多例
     * @author by@Deng
     * @date 2017/12/16 下午4:56
     */
    public EchoSocket() {
        System.out.println("验证我是多例的");
    }

    @OnOpen
    public void open(Session session) throws Exception {
        //session代表一个管道会话

        System.out.println("我连接上了"+session.getId());

        //获取参数
        username = URLDecoder.decode(session.getQueryString().split("=")[1],"utf-8");
        System.out.println(username);
        stringSet.add(username);

    }


    @OnMessage
    public void message(Session session, String message){
        try {

            System.out.println("客户端说："+message);
            session.getBasicRemote().sendText("服务器端说:"+message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @OnClose
    public void close(Session session){
        System.out.println(session.getId()+"通道关闭了");
    }


    /**
     * 发生错误时调用
     * @param session
     * @param error
    */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("发生错误");
         error.printStackTrace();
     }


}
