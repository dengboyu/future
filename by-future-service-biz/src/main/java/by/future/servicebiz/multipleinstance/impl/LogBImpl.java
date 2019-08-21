package by.future.servicebiz.multipleinstance.impl;

import by.future.servicebiz.multipleinstance.ILog;
import org.springframework.stereotype.Service;

/**
 * @author by@Deng
 * @create 2019-08-21 22:47
 */
@Service
public class LogBImpl implements ILog {

    @Override
    public void info() {
        System.out.println("我是第二个实现类");
    }
}
