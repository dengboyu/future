package by.future.mq.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbit工具类
 *
 * @author by@Deng
 * @create 2018-03-08 15:06
 */
public class ConnectionUtils {

    /**
     * 获取rabbitmq的链接
     * @author by@Deng
     * @date 2018/3/8 下午3:07
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个链接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置服务地址
        factory.setHost("127.0.0.1");

        //设置端口号 - AMQP协议
        factory.setPort(5672);

        //设置v_host(相当于数据库)
        factory.setVirtualHost("admin");

        //设置用户名
        factory.setUsername("admin");
        factory.setPassword("admin");

        return factory.newConnection();

    }

}
