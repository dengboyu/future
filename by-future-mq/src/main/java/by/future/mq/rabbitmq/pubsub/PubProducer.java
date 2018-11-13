package by.future.mq.rabbitmq.pubsub;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author by@Deng
 * @create 2018-03-09 16:50
 */
public class PubProducer {

    private static final String EXCHANGE_NAME="test_pubsub";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//分发

        //发送消息
        String msg = "hello,发布订阅";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        System.out.println("send-->"+msg);

        channel.close();
        connection.close();

    }
}
