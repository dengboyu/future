package by.future.common.pattern.singleton;


/**
 * 双重校验锁方式获取单例demo
 * DCL必须要volatile
 *
 * @author by@Deng
 * @create 2019-08-21 00:36
 */
public class PersonSingle {

    private static volatile PersonSingle personSingle = null;

    private PersonSingle() {
    }

    /**
     * 双重校验锁方式获取单例
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
