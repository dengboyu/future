package by.future.mq.rabbitmq.tx;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author by@Deng
 * @create 2018-03-10 15:33
 */
public class txRec {

    private static final String QUEUE_NAME="queue_tx_test";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建一个队列
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
