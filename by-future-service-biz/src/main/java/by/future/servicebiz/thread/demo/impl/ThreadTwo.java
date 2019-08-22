package by.future.servicebiz.thread.demo.impl;

import by.future.servicebiz.thread.demo.IThreadTwo;
import org.springframework.stereotype.Service;

/**
 * @author by@Deng
 * @create 2018-09-08 21:50
 */
@Service
public class ThreadTwo implements IThreadTwo {

    @Override
    public void test(){
        System.out.println("我是threadTwo测试");
    }

}
