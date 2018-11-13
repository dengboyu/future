package by.future.mq.rabbitmq.workQueue;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author by@Deng
 * @create 2018-03-08 17:24
 */
public class Comsumer3 {

    private static final String QUEUE_NAME = "test_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //收到消息
                System.out.println("consumer3收到消息:"+new String(body,"utf-8"));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //注册监听
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
