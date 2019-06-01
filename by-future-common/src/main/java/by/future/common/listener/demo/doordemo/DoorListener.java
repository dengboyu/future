package by.future.common.listener.demo.doordemo;


/**
 * 事件监听器
 *
 * @author by@Deng
 * @create 2019-06-01 13:00
 */
public interface DoorListener {

    void openDoorListener(DoorEvent doorEvent);

    void closeDoorListener(DoorEvent doorEvent);

}
