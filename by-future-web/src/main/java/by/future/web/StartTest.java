package by.future.web;

import by.future.common.utils.MyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author by@Deng
 * @create 2020-04-04 21:23
 */
@ComponentScan("by.future")
@Configuration
public class StartTest {

    private static Logger logger = LoggerFactory.getLogger(StartTest.class);
//    private static Logger logger = LogManager.getLogger(StartTest.class);

    public static void main(String[] args) {
        
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(StartTest.class);

//        logger.debug("我是info级别的");




        MyClassLoader myClassLoader1 = new MyClassLoader();
        MyClassLoader myClassLoader2 = new MyClassLoader();
        try {
            myClassLoader1.loadClass("by.future.web.user.UserLogin");
            myClassLoader2.loadClass("by.future.web.user.UserLogin");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
