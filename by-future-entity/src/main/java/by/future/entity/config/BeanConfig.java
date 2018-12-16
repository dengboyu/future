package by.future.entity.config;


/**
 * bean实例化
 *
 * @Author：by@Deng
 * @Date：2018/12/14 17:19
 */
public class BeanConfig {

    private String name;

    private String address;

    private int age;

    public BeanConfig(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public void init(){
        System.out.println("BeanConfig被初始化了");
    }


    public void destroy(){
        System.out.println("BeanConfig被销毁了");
    }



}
