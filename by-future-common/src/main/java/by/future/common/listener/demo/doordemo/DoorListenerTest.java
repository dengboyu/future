package by.future.common.listener.demo.doordemo;

import java.util.Arrays;

/**
 * 监听器测试方法
 *
 * @author by@Deng
 * @create 2019-06-01 13:10
 */
public class DoorListenerTest {

    public static void main(String[] args) {

        IDoorSource doorSource = new DoorSource();

        //监听器1
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

        //监听器2
        DoorListener doorListener2 = new DoorListener() {
            @Override
            public void openDoorListener(DoorEvent doorEvent) {
                System.out.println("学生们立刻安静，认真学习了");
            }

            @Override
            public void closeDoorListener(DoorEvent doorEvent) {
                System.out.println("学生们依然认真学习");
            }
        };

        //将监听器注册到事件源上
        doorSource.registerListener(doorListener);
        doorSource.registerListener(doorListener2);

        doorSource.openDoor();
        doorSource.closeDoor();

        System.out.println(Arrays.toString(doorSource.findDoorListeners()));


    }


}
