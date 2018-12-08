package by.future.web.initrun;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 多个启动初始化数据用order来定顺序
 * ps: order一般从5开始，留前几个值可扩展系统
 *
 * @Author：by@Deng
 * @Date：2018/12/8 16:42
 */
@Component
@Order(6)
public class InitRunTest2 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("第二个被初始化的数据");
    }


}
