package by.future.common.listener.demo.doordemo;

/**
 * 事件对象，封装事件源
 *
 * @author by@Deng
 * @create 2019-06-01 13:03
 */
public class DoorEvent {

    private DoorSource doorSource;

    public DoorEvent(DoorSource doorSource) {
        this.doorSource = doorSource;
    }

    public DoorSource getDoorSource() {
        return doorSource;
    }

    public void setDoorSource(DoorSource doorSource) {
        this.doorSource = doorSource;
    }
}
