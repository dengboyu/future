package by.future.web.user;


import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * @Author：by@Deng
 * @Date：2020/3/18 19:39
 */
public class TestSPI implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("测试成功..........................");
    }
}
