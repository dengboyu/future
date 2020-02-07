package by.future.common.pattern.order;

import by.future.common.pattern.order.impl.LightImpl;
import by.future.common.pattern.order.impl.LightOnCommand;
import by.future.common.pattern.order.impl.RemoteControl;

/**
 * @author by@Deng
 * @create 2020-02-05 18:09
 */
public class RemoteTest {


    public static void main(String[] args) {

        RemoteControl remoteControl = new RemoteControl();
        Light light = new LightImpl();
        LightOnCommand lightOnCommand = new LightOnCommand(light);

        remoteControl.setCommand(lightOnCommand);
        remoteControl.buttonWasPressed();


    }

}
