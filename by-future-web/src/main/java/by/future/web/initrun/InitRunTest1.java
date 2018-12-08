package by.future.web.initrun;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在spring beans都初始化之后执行，在SprigApplication.run()之前执行，进行初始化
 * eg: 用于项目启动的时候需要初始化一些操作，比如初始化线程池，提前加载好加密证书等
 *
 * @Author：by@Deng
 * @Date：2018/12/8 16:34
 */
@Component
@Order(5)
public class InitRunTest1 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        System.out.println("我要开始初始化数据了");
    }

}
