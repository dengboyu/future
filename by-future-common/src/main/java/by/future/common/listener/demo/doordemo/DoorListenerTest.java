package by.future.common.listener.demo.doordemo;

/**
 * 监听器测试方法
 *
 * @author by@Deng
 * @create 2019-06-01 13:10
 */
public class DoorListenerTest {

    public static void main(String[] args) {

        DoorSource doorSource = new DoorSource();

        //监听器
        DoorListener doorListener = new DoorListener() {
            @Override
            public void openDoorListener(DoorEvent doorEvent) {
                System.out.println("学生们立刻安静，假装学习了");
            }

            @Override
            public void closeDoorListener(DoorEvent doorEvent) {
                System.out.println("学生们又开始聊天了");
            }
        };

        //将写好的监听器注册到事件源上
        doorSource.registerListener(doorListener);

        doorSource.openDoor();
        doorSource.closeDoor();


    }


}
