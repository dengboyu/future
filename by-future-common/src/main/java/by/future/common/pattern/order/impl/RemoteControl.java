package by.future.common.pattern.order.impl;

import by.future.common.pattern.order.Command;

/**
 * 遥控器
 *
 * @author by@Deng
 * @create 2020-02-05 18:07
 */
public class RemoteControl {

    private Command command;

    public RemoteControl() {
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void buttonWasPressed(){
        command.execute();
    }


}
