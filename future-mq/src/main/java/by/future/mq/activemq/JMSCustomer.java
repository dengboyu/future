package by.future.mq.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 *
 * @author by@Deng
 * @create 2017-10-23 10:32
 */
public class JMSCustomer {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL= ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {

        //连接工厂
        ConnectionFactory cf = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        //获取连接
        Connection connection=null;

        try {

            connection = cf.createConnection();
            connection.start();

            //发送或者接收消息的线程
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE); //是否加事务

            //消息目的地
            //点对点，一对一生产模式
//            Destination destination = session.createQueue("firstQueue1");
            //发布订阅模式，一对多模式
            Destination destination = session.createTopic("firstTopic1");

            //消费者
            MessageConsumer mc = session.createConsumer(destination);   //创建消费者

            //注册一个监听器
            mc.setMessageListener(new JMSListener());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}
