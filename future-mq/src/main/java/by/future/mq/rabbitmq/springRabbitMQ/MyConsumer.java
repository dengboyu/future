package by.future.mq.rabbitmq.springRabbitMQ;

/**
 * @author by@Deng
 * @create 2018-03-10 17:48
 */
public class MyConsumer {


    /**
     * 具体执行业务
     * @author by@Deng
     * @date 2018/3/10 下午5:49
     */
    public void listen(String foo){
        System.out.println("消费者:"+foo);
    }

}
