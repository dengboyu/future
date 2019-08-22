package by.future.servicebiz.thread.entity;

import org.springframework.stereotype.Component;

/**
 * @author by@Deng
 * @create 2018-09-08 22:41
 */
@Component
public class ThreadTestTwoEntity {

    private String stu;
    private String address;
    private int age;

    public String getStu() {
        return stu;
    }

    public void setStu(String stu) {
        this.stu = stu;
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
}
