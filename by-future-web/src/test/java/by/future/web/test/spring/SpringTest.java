package by.future.web.test.spring;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring的test
 *
 * @Author：by@Deng
 * @Date：2018/12/23 17:06
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration   //需要启动配置文件
public class SpringTest {

    @Test
    public void test(){

        System.out.println("我们");
    }


}
