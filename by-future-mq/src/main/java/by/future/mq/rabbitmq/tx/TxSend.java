package by.future.mq.rabbitmq.tx;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author by@Deng
 * @create 2018-03-10 15:24
 */
public class TxSend {

    private static final String EXCHANGE_NAME="tx_text";
    private static final String QUEUE_NAME="queue_tx_test";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //创建一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "hello,tx";

        try {
            channel.txSelect(); //开启事物模式

            //发送信息
            channel.basicPublish("", QUEUE_NAME,null,msg.getBytes());

//            int i =1/0;
            System.out.println("发送了消息");
            channel.txCommit();
        } catch (Exception e) {
            e.printStackTrace();
            channel.txRollback();
            System.out.println("sendMessage失败了");
        } finally {
            channel.close();
            connection.close();
        }


    }


}
