package by.future.dal.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * @Author：by@Deng
 * @Date：2018/12/16 16:47
 */
@Configuration
@Profile("pro")
public class ProDataConfig implements DataConfig {


    @Override
    public DataSource dataSource(){
        System.out.println("我是pro");
        return null;
    }


}
