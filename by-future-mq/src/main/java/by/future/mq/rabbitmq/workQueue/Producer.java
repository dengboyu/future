package by.future.mq.rabbitmq.workQueue;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author by@Deng
 * @create 2018-03-08 16:54
 */
public class Producer {

    private static final String QUEUE_NAME = "test_work";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        for(int i=1;i<=20;i++){
            String msg = "发消息的次数:hello"+i;

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("发送消息次数:hello"+i);

            Thread.sleep(100);
        }

        channel.close();
        connection.close();

    }

}
