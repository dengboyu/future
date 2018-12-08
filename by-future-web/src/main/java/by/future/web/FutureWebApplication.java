package by.future.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.future")
//@EnableScheduling(定时任务启动注解)
public class FutureWebApplication {

    public static void main(String[] args) {
        try{

            SpringApplication.run(FutureWebApplication.class, args);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
