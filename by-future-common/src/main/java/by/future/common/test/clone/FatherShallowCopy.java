package by.future.common.test.clone;

/**
 * 浅拷贝
 *
 * @author by@Deng
 * @create 2019-08-31 22:31
 */
public class FatherShallowCopy implements Cloneable{

    private int age;
    private String name;


    @Override
    protected FatherShallowCopy clone() throws CloneNotSupportedException {
        FatherShallowCopy o = null;
        try {
            o = (FatherShallowCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
