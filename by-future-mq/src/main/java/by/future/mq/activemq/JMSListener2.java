package by.future.mq.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author by@Deng
 * @create 2018-03-06 15:59
 */
public class JMSListener2 implements MessageListener {


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者二收到的消息"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
