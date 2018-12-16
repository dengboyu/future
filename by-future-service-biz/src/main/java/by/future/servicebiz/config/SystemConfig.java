package by.future.servicebiz.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 系统配置类
 * @param :@Import导入其他配置类简化了容器实例化，因为只需要处理一个类，而不是在构造期间记住大量的@Configuration类
 *
 * @Author：by@Deng
 * @Date：2018/12/14 17:17
 */
@Configuration
@Import({ConfigTest.class})
public class SystemConfig {





}
