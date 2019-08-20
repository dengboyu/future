package by.future.common.singleton;

import by.future.entity.test.PersonTest;

/**
 * 饿汉式单例demo
 *
 * @author by@Deng
 * @create 2019-08-21 00:36
 */
public class PersonSingle {

    private static PersonSingle personSingle;

    private PersonSingle() {
    }

    /**
     * 饿汉式单例
     *
     * @Author: by@Deng
     * @Date: 2019-08-21 00:51
     */
    public static PersonSingle getInstance() {

        if (personSingle == null) {
            synchronized (PersonSingle.class){
                if(personSingle ==null){
                personSingle = new PersonSingle();
                }
            }

        }
        return personSingle;
    }


}
