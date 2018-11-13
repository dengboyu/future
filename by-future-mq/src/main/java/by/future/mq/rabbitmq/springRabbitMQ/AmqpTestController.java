package by.future.mq.rabbitmq.springRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author by@Deng
 * @create 2018-03-10 17:53
 */
@RestController
public class AmqpTestController {

    @Resource
    private RabbitTemplate rabbitTemplate;


    /**
     *
     * @author by@Deng
     * @date 2018/3/10 下午8:43
     */
    @GetMapping("/sendMsg")
    public String sendMsg(){

        rabbitTemplate.convertAndSend("hello world");

        return "success";
    }


}
