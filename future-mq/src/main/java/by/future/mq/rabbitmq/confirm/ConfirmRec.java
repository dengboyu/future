package by.future.mq.rabbitmq.confirm;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author by@Deng
 * @create 2018-03-10 15:53
 */
public class ConfirmRec {

    private static final String QUEUE_NAME="test_confirm_send";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //消费者
        channel.basicConsume(QUEUE_NAME,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("msg是:"+msg);

            }
        });

    }


}
