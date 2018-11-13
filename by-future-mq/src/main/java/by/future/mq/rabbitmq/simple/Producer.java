package by.future.mq.rabbitmq.simple;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列example
 *
 * @author by@Deng
 * @create 2018-03-08 15:01
 */
public class Producer {

    private static final String QUEUE_NAME ="test_simple_u1";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取链接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发送消息
        String msg = "hello simple，这是我发的一条消息";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        System.out.println("msg发送成功了");

        //关闭通道和链接
        channel.close();
        connection.close();

    }

}
