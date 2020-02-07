package by.future.common.pattern.order.impl;

import by.future.common.pattern.order.Light;
import org.springframework.stereotype.Service;

/**
 * 灯
 *
 * @author by@Deng
 * @create 2020-02-05 18:01
 */
@Service
public class LightImpl implements Light {


    @Override
    public void lightOn() {
        System.out.println("light is on");
    }

    @Override
    public void lightOff() {
        System.out.println("light is off");
    }

}
