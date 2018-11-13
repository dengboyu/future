package by.future.mq.rabbitmq.pubsub;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author by@Deng
 * @create 2018-03-09 17:52
 */
public class Subconsumer2 {


    private static final String QUEUE_NAME = "test_sms";
    private static final String EXCHANGE_NAME = "test_pubsub";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //队列声明
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //保证一次只分发一个
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");

                System.out.println("接收到的消息:--->"+msg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }finally {
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }

        };

        boolean autoAck = false;    //关闭自动应答
        channel.basicConsume(QUEUE_NAME,autoAck,consumer);

    }

}
