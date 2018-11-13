package by.future.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * jms生产者
 *
 * @author by@Deng
 * @create 2017-10-23 10:06
 */
public class JMSProducer {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;  //默认登录名
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;  //默认登录密码
    private static final String BROKEURL= ActiveMQConnection.DEFAULT_BROKER_URL;    //连接地址
    private static final int SENDNUM = 10;  //发送消息数量

    public static void main(String[] args) throws JMSException {
        //实例化连接工厂
        ConnectionFactory cf = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        Connection connection = null;

        try {
            connection = cf.createConnection(); //通过连接工厂获取连接
            connection.start(); //启动连接

            //发送消息的线程会话
            Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);  //是否加事务

            //消息目的地
            //点对点，一对一生产模式
//            Destination destination = session.createQueue("firstQueue1"); //创建点对点消息队列
            //发布订阅模式，一对多模式
            Destination destination = session.createTopic("firstTopic1"); //创建订阅消息队列

            //消息生产者
            MessageProducer mp = session.createProducer(destination);    //创建消息生产者

            //发送消息
            sendMessage(session,mp);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                connection.close();
            }
        }

    }

    /**
     * 发送消息
     * @author by@Deng
     * @date 2017/10/23 上午10:21
     */
    public static void sendMessage(Session session,MessageProducer mp) throws JMSException {

        for(int i=0;i<SENDNUM;i++){
            TextMessage tm = session.createTextMessage("ACTIVEMQ"+i);
            System.out.println("发送消息"+i);
            mp.send(tm);
        }

    }


}
