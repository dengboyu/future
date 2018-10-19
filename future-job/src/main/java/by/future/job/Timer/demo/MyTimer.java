package by.future.job.Timer.demo;

import java.util.Timer;

/**
 * @author by@Deng
 * @create 2018-05-27 22:55
 */
public class MyTimer {

    public static void main(String[] args) {
        Timer timer = new Timer();

        MyTimerTask myTimerTask = new MyTimerTask("no.1");
        timer.schedule(myTimerTask,4000L,2000L);

    }


}
