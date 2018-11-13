package by.future.mq.rabbitmq.simple;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 接受者
 *
 * @author by@Deng
 * @create 2018-03-08 15:38
 */
public class Consumer {

    private static final String QUEUE_NAME ="test_simple_u1";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取链接
        Connection connection = ConnectionUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //获取消息
        DefaultConsumer consumer = new DefaultConsumer(channel){
            //一旦有消息进入队列，就会触发这个事件
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("接收到的消息--->"+msg);
            }
        };

        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }

}
