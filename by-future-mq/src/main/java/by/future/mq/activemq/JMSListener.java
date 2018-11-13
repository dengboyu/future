package by.future.mq.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消费者监听
 *
 * @author by@Deng
 * @create 2017-10-23 10:42
 */
public class JMSListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者一收到的消息"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
