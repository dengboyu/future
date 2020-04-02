package by.future.common.listener.demo.doordemo;


/**
 * @Author：by@Deng
 * @Date：2020/4/1 9:27
 */
public interface IDoorSource {

    void registerListener(DoorListener doorListener);

    void removeListener(DoorListener doorListener);

    DoorListener[] findDoorListeners();

    void openDoor();

    void closeDoor();

}
