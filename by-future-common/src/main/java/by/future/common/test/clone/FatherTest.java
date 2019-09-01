package by.future.common.test.clone;

import by.future.common.singleton.PersonSingle;

import java.nio.channels.Pipe;

/**
 * @author by@Deng
 * @create 2019-08-31 22:31
 */
public class FatherTest {

    private int age;
    private String name;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
