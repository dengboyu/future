package by.future.mq.rabbitmq.confirm;

import by.future.mq.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 普通模式
 * @author by@Deng
 * @create 2018-03-10 15:51
 */
public class ConfirmSend {

    private static final String QUEUE_NAME="test_confirm_send";

    public static void main(String[] args) throws Exception{

        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //将channel设置为confirm模式
        channel.confirmSelect();

        String msg = "hello,tx";

        //发送信息
        channel.basicPublish("", QUEUE_NAME,null,msg.getBytes());

//        int i =1/0;
        if(!channel.waitForConfirms()){
            System.out.println("发送失败");
        }else{
            System.out.println("发送了消息");
        }
        channel.close();
        connection.close();

    }
}
