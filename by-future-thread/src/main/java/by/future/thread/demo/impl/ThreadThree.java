package by.future.thread.demo.impl;


import by.future.thread.demo.IThreadTwo;
import org.springframework.stereotype.Service;

/**
 * @Author：by@Deng
 * @Date：2019/3/23 12:18
 */
@Service
public class ThreadThree implements IThreadTwo {

    @Override
    public void test() {
        System.out.println("第二个实现");
    }

    public void test1(){
        System.out.println("测试测试");
    }
}
