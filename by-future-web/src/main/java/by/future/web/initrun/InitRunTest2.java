package by.future.web.initrun;


import by.future.common.cache.MapCache;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 多个启动初始化数据用order来定顺序
 * ps: order一般从5开始，留前几个值可扩展系统
 *
 * @Author：by@Deng
 * @Date：2018/12/8 16:42
 */
@Component
@Order(6)
public class InitRunTest2 implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        System.out.println("开始加载resources下的资源...");

        File file = ResourceUtils.getFile("classpath:static/test.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String message = IOUtils.toString(br);
        MapCache.setCacheMap("test",message);

    }


}
