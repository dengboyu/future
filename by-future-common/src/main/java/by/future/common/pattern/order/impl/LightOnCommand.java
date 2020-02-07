package by.future.common.pattern.order.impl;

import by.future.common.pattern.order.Command;
import by.future.common.pattern.order.Light;

/**
 * 灯的开
 *
 * @author by@Deng
 * @create 2020-02-05 18:00
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOn();
    }

}
