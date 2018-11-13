package by.future.mq.rabbitmq.topic;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author by@Deng
 * @create 2018-03-10 09:43
 */
public class ProducerSend {

    private static final String EXCHANGE_NAME="test_topic";

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        String msg = "topic主题模式";
        channel.basicPublish(EXCHANGE_NAME,"goods.add",null,msg.getBytes());
        System.out.println("发送了消息:"+msg);

        channel.close();
        connection.close();

    }

}
