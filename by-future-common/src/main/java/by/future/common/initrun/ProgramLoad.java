package by.future.common.initrun;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @Author：by@Deng
 * @Date：2019/6/17 15:02
 */
@Order(3)
@Service
public class ProgramLoad implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动后被加载了");
    }
}
