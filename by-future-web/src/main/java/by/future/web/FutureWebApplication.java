package by.future.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "by.future")
public class FutureWebApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(FutureWebApplication.class, args);

//            org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'requestMappingHandlerMapping' defined in class path resource [org/springframework/boot/autoconfigure/web/servlet/WebMvcAutoConfiguration$EnableWebMvcConfiguration.class]: Initialization of bean failed; nested exception is java.lang.NoSuchMethodError: org.springframework.web.servlet.handler.AbstractHandlerMapping.obtainApplicationContext()Lorg/springframework/context/ApplicationContext;
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
