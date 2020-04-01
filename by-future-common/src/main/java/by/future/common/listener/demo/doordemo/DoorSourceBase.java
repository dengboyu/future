package by.future.common.listener.demo.doordemo;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author：by@Deng
 * @Date：2020/4/1 10:03
 */
public abstract class DoorSourceBase implements IDoorSource{

    private List<DoorListener> doorListenerList = new CopyOnWriteArrayList<>();

    List<DoorListener> getDoorListenerList() {
        return doorListenerList;
    }


    /**
     * 将监听器注册到事件源上
     *
     * @Author: by@Deng
     * @Date: 2019-06-01 13:07
     */
    @Override
    public void registerListener(DoorListener doorListener){
        getDoorListenerList().add(doorListener);
    }


    @Override
    public void removeListener(DoorListener doorListener) {
        doorListenerList.remove(doorListener);
    }


    @Override
    public DoorListener[] findDoorListeners() {
        DoorListener[] doorListeners = new DoorListener[doorListenerList.size()];
        return this.doorListenerList.toArray(doorListeners);
    }

}
