package by.future.common.listener.demo.doordemo;

/**
 * 事件源
 *
 * @author by@Deng
 * @create 2019-06-01 12:59
 */
public class DoorSource {

    private DoorListener doorListener;


    /**
     * 将监听器注册到事件源上
     *
     * @Author: by@Deng
     * @Date: 2019-06-01 13:07
     */
    public void registerListener(DoorListener doorListener){
        this.doorListener = doorListener;
    }

    public void openDoor(){

        System.out.println("门开了");

        if(doorListener!=null){
            doorListener.openDoorListener(new DoorEvent(this));
        }
    }


    public void closeDoor(){

        System.out.println("门关闭了");

        if(doorListener!=null){
            doorListener.closeDoorListener(new DoorEvent(this));
        }
    }

}
