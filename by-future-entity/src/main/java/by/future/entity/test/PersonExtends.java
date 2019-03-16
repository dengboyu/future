package by.future.entity.test;


/**
 * @Author：by@Deng
 * @Date：2019/2/25 17:02
 */
public class PersonExtends extends PersonTest {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonExtends{" +
                "address='" + address + '\'' +
                '}';
    }
}
