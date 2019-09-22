package by.future.common.test.clone;

/**
 * 深拷贝，一般使用序列化和返序列化来做
 *
 * @author by@Deng
 * @create 2019-09-21 16:29
 */
public class FatherDeepCopy {

    private String address;
    private int age;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        FatherDeepCopy o = null;
        try {
            o = (FatherDeepCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
