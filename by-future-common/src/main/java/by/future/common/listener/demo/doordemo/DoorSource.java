package by.future.common.listener.demo.doordemo;

/**
 * 事件源
 *
 * @author by@Deng
 * @create 2019-06-01 12:59
 */
public class DoorSource extends DoorSourceBase{

    private DoorListener doorListener;


    /**
     * 将监听器注册到事件源上
     *
     * @Author: by@Deng
     * @Date: 2019-06-01 13:07
     */
    @Override
    public void registerListener(DoorListener doorListener){
        this.doorListener = doorListener;
    }


    @Override
    public void openDoor(){

        System.out.println("门开了");

        /*if(doorListener!=null){
            doorListener.openDoorListener(new DoorEvent(this));
        }*/

        getDoorListenerList().forEach(n->n.openDoorListener(new DoorEvent(this)));
    }


    @Override
    public void closeDoor(){

        System.out.println("门关闭了");

        /*if(doorListener!=null){
            doorListener.closeDoorListener(new DoorEvent(this));
        }*/

        getDoorListenerList().forEach(n->n.closeDoorListener(new DoorEvent(this)));
    }



}
