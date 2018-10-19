package by.future.mq.rabbitmq.topic;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author by@Deng
 * @create 2018-03-10 10:57
 */
public class ConsumerRec1 {

    private static final String EXCHANGE_NAME="test_topic";
    private static final String QUEUE_NAME="topic_consumer1";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //将队列绑定到交换机上
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"goods.add");

        //每次发送一个
        channel.basicQos(1);

        //监听事件
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("topic1接收到的消息:"+msg);

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
