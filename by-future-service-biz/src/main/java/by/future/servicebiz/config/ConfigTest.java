package by.future.servicebiz.config;


import by.future.entity.config.BeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类1
 * @param: @Configuration是配置类，@Component注解别名
 *
 * @Author：by@Deng
 * @Date：2018/12/14 17:18
 */
@Configuration
public class ConfigTest {



    @Bean(initMethod = "init",destroyMethod = "destroy")
    public BeanConfig beanConfig(){
        return new BeanConfig("测试","上海市嘉定区",18);

    }


}
